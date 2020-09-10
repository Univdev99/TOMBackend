package com.tom.common.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;

import com.tom.common.beans.QueryParams;

/**
 * @author anushah
 *
 * @param <T>
 */
public interface GenericDao<T extends Serializable> {

	public T saveOrUpdate(final T o) throws Exception;

	public Long save(final T o) throws Exception;

	public T get(final Class<T> type, final Long id) throws Exception;

	public T get(final Class<T> type, final Integer id) throws Exception;

	public T get(final Class<T> type, final String id) throws Exception;

	public List<T> getAll(final Class<T> type) throws Exception;

	public void delete(final Object object) throws Exception;

	public T updateEntityOnDelete(final T o) throws Exception;

	public void saveOrUpdateAll(final Collection<T> objects) throws Exception;

	public T merge(T object) throws Exception;

	public void deleteALL(final Collection<T> objects) throws Exception;

	/**
	 * 
	 * @author CCJoshi
	 * @param sqlQuery
	 * @param column
	 * @return column.put("alias",Payment.class); or column.put("alias","map");
	 */
	public List<T> executeSqlSelect(StringBuilder query, Map<String, Object> column) throws Exception;

	/**
	 * 
	 * @author pbhavsa
	 * @param sqlQuery
	 * @param column
	 * @return column.put("alias",Payment.class); or column.put("alias","map");
	 */
	public List<Map<String, Object>> executeSqlSelectMap(StringBuilder query, Map<String, Object> column) throws Exception;

	/**
	 * 
	 * @param query
	 * @param column
	 * @return
	 * @throws Exception
	 * @author BRanpariya
	 * @ModifiedBy BRanpariya
	 * @ModifiedDate Mar 17, 2017
	 */
	public List<Map<String, Object>> executeHqlSelectMap(StringBuilder query, Map<String, Object> column) throws Exception;

	/**
	 * 
	 * @author CCJoshi
	 * @param sqlQuery
	 * @param column
	 * @return column.put("alias",Payment.class); or column.put("alias","map");
	 * 
	 */
	public List<T> executeHqlSelect(StringBuilder query, Map<String, Object> column) throws Exception;

	/**
	 * 
	 * @author CCJoshi
	 * @param query
	 * @param column
	 * @return column.put("alias",Payment.class); or column.put("alias","map");
	 * 
	 */
	public Integer executeUpdate(StringBuilder query, Map<String, Object> column) throws Exception;

	/**
	 * 
	 * @author CCJoshi
	 * @param query
	 * @param column
	 * @return column.put("alias",SAALFlexibleWorkFlow.class); or column.put("alias","map");
	 * 
	 */
	public Integer executeSqlUpdate(StringBuilder query, Map<String, Object> column) throws Exception;

	/**
	 * @author CCJoshi
	 * @param spName
	 * @param param
	 * @return
	 */
	public int executeCommonSP(String spName, String... param) throws Exception;

	/**
	 * Clear object from session cache.
	 * 
	 * @author CCJoshi
	 * @param object
	 */
	public void evictObject(final Object object) throws Exception;

	/**
	 * 
	 * @param spName
	 * @param param
	 * @return
	 * @throws Exception
	 * @author pbhavsar
	 * @ModifiedBy pbhavsar
	 * @ModifiedDate Mar 1, 2017
	 */
	public List<Map<String, Object>> getDataFromSP(String spName, String... param) throws Exception;

	public List<T> getListByRistrictions(Class<T> c, Map<String, Object> map, String[] columnNames, Map<String, Object> others) throws Exception;

	/**
	 * 
	 * @author CCJoshi
	 * @param sqlQuery
	 * @param column
	 * @return column.put("alias",Payment.class); or column.put("alias","map");
	 */
	public List<String> returnAsStringList(StringBuilder query, Map<String, Object> column) throws Exception;

	public List<Integer> returnAsIntegerList(StringBuilder query, Map<String, Object> column) throws Exception;

	/**
	 * Reutrns List of String for HQL
	 * 
	 * @param query
	 * @param column
	 * @return
	 * @throws Exception
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Mar 3, 2017
	 */
	public List<String> returnAsStringListHQL(StringBuilder query, Map<String, Object> column) throws Exception;

	/**
	 * For Creating Criteria on given class
	 * 
	 * @param c
	 * @param alias
	 * @return
	 * @throws Exception
	 * @author anushah
	 * @ModifiedBy anushah
	 * @ModifiedDate Mar 7, 2017
	 */
	public Criteria createCriteria(Class<T> c, String alias) throws Exception;

	/**
	 * For Executing Criteria
	 * 
	 * @param cr
	 * @return
	 * @throws Exception
	 * @author anushah
	 * @ModifiedBy anushah
	 * @ModifiedDate Mar 7, 2017
	 */
	@SuppressWarnings("rawtypes")
	public List executeCriteria(Criteria cr) throws Exception;

	/**
	 * 
	 * For executing criteria with additional filters sent as List
	 * 
	 * @param cr
	 * @param queryParamList
	 * @return
	 * @throws Exception
	 * 
	 * @author nbhambhani
	 * @ModifiedBy nbhambhani
	 * @ModifiedDate May 10, 2017
	 */
	public List<T> executeCriteria(Criteria cr, List<QueryParams> queryParamList) throws Exception;

	/**
	 * @author pbhavsar
	 * @param class
	 * -> Ex : CustomDrug.class
	 * @param required
	 * columns -> Ex : String[] columnNames = {"customDrugId"}
	 * @param List<QueryParams>
	 * queryParamList Ex : sequence : propertyName = "drugName", condition = QueryConstants.LIKE, propertyValue = "test" Query Ex: List<QueryParams> queryParamList = new ArrayList<>();
	 * queryParamList.add(new QueryParams(IS_DELETED, QueryConstants.EQ, 0)); queryParamList.add(new QueryParams(DRUG_NAME, QueryConstants.STARTS_WITH, searchParam)); queryParamList.add(new
	 * QueryParams(0, QueryConstants.RESULT_COUNT, 20)); return customDrugDao.getListByCriteria(CustomDrug.class, null, queryParamList);
	 * 
	 * @return list
	 */
	List<T> getListByCriteria(Class<T> c, String[] columnNames, List<QueryParams> queryParamList) throws Exception;

	/**
	 * @author VDave
	 * @param queryList
	 * add all native query to queryList which will be executed in batch.
	 */
	public void executeSqlBatch(List<StringBuilder> queryList);

	/**
	 * @author VDave
	 * @param sqlQuery
	 * @return get list of bigint from native query for one column ex. id..
	 */
	public List<BigInteger> geBigIntList(String sqlQuery);

	public List<T> executeSqlSelectList(StringBuilder spQuery, Map<String, Object> spMap, Class<T> class1) throws Exception;

	/**
	 * @author aaShah
	 * @param spName
	 * @param param
	 * @return
	 */
	public List executeCommonSPList(String spName, String... param) throws Exception;

	/**
	 * @author aaShah
	 * @param list
	 * of object
	 * @param param
	 * @return
	 */
	public void evictObject(Collection<T> objects) throws Exception;

}
