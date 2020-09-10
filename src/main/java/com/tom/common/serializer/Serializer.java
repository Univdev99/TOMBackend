/**
 * 
 */
package com.tom.common.serializer;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tom.common.beans.ObjectMapper;
import com.tom.common.view.View;

/**
 * This generic serializer returns the json with required properties based on the View.
 * 
 * @author RGandhi
 *
 */
public class Serializer extends JsonSerializer<Object> {
	@Override
	public void serialize(Object object, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
		ObjectMapper mapper = ObjectMapper.getObjectMapper();
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setConfig(mapper.getSerializationConfig().withView(View.GenericView.class));
		jsonGenerator.setCodec(mapper);
		jsonGenerator.writeObject(object);
	}
}
