package com.tom.common.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.jdbc.Work;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tom.common.beans.QueryParams;
import com.tom.common.beans.QueryParams.QueryConstants;
import com.tom.common.constant.Constant;
import com.tom.util.StringUtil;

@Repository
@Primary
public class GenericDaoImpl<T extends Serializable> extends HibernateDaoSupport implements GenericDao<T>, Constant {
	static {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
	}
	private static final Logger loggerr = LoggerFactory.getLogger(GenericDaoImpl.class);

	
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sessionFactory;

	@Autowired
	public GenericDaoImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	protected void initDao() {
		// do nothing
	}

	@Override
	@Transactional(readOnly = false)
	public T saveOrUpdate(final T o) throws Exception {
		loggerr.debug("Execute method : save()");
		getHibernateTemplate().saveOrUpdate(o);
		return o;
	}

	@Override
	@Transactional
	public Long save(final T o) throws Exception {
		loggerr.debug("Execute method : save()");
		return (Long) getHibernateTemplate().save(o);
	}

	@Override
	@Transactional
	public void saveOrUpdateAll(final Collection<T> objects) throws Exception {
		loggerr.debug("Execute method : saveOrUpdateAll()");
		for (Object bean : objects) {
			getHibernateTemplate().saveOrUpdate(bean);
		}
	}

	@Override
	public T get(final Class<T> type, final Long id) throws Exception {
		loggerr.debug("Execute method : get(Long)");
		return getHibernateTemplate().get(type, id);
	}

	@Override
	public T get(final Class<T> type, final String id) throws Exception {
		loggerr.debug("Execute method : get(String)");
		return getHibernateTemplate().get(type, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(final Class<T> type) throws Exception {
		loggerr.debug("Execute method : getAll()");
		Criteria crit = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(type);
		return crit.list();
	}

	@Override
	@Transactional
	public void delete(final Object object) throws Exception {
		loggerr.debug("Execute method : delete()");
		getHibernateTemplate().delete(object);
	}

	@Override
	@Transactional
	public T updateEntityOnDelete(final T o) throws Exception {
		loggerr.debug("Execute method : updateEntityOnDelete()");
		getHibernateTemplate().saveOrUpdate(o);
		return o;
	}

	@Override
	@Transactional
	public T merge(T object) throws Exception {
		loggerr.debug("Execute method : merge()");
		return getHibernateTemplate().merge(object);
	}

	@Override
	@Transactional
	public void deleteALL(final Collection<T> objects) throws Exception {
		loggerr.debug("Execute method : deleteALL()");
		for (Object bean : objects) {
			getHibernateTemplate().delete(bean);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> executeSqlSelect(StringBuilder query, Map<String, Object> column) throws Exception {
		loggerr.debug("Execute method : executeSqlSelect()");
		Query queryObj = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(query.toString());
		if (null != column) {
			setQueryParameter(queryObj, column);
		}
		// queryObj.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return queryObj.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> executeSqlSelectMap(StringBuilder query, Map<String, Object> column) throws Exception {
		loggerr.debug("Execute method : executeSqlSelectMap()");
		Query queryObj = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(query.toString());
		if (null != column) {
			setQueryParameter(queryObj, column);
		}
		queryObj.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP); // because
																		// if
																		// return
																		// type
																		// is
																		// map
																		// then
																		// no
																		// need
																		// of
																		// alias
																		// param
		return queryObj.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> executeHqlSelectMap(StringBuilder query, Map<String, Object> column) throws Exception {
		loggerr.debug("Execute method : executeHqlSelectMap()");
		Query queryObj = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
		if (null != column) {
			setQueryParameter(queryObj, column);
		}
		queryObj.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP); // because
																		// if
																		// return
																		// type
																		// is
																		// map
																		// then
																		// no
																		// need
																		// of
																		// alias
																		// param
		return queryObj.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> returnAsStringList(StringBuilder query, Map<String, Object> column) throws Exception {
		loggerr.debug("Execute method : returnAsStringList()");
		Query queryObj = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(query.toString());
		if (null != column) {
			setQueryParameter(queryObj, column);
		}
		return queryObj.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> returnAsStringListHQL(StringBuilder query, Map<String, Object> column) throws Exception {
		loggerr.debug("Execute method : returnAsStringList_HQL()");
		Query queryObj = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
		if (null != column) {
			setQueryParameter(queryObj, column);
		}
		return queryObj.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> executeHqlSelect(StringBuilder query, Map<String, Object> column) throws Exception {
		loggerr.debug("Execute method : executeHqlSelect()");
		Query queryObj = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
		if (null != column) {
			setQueryParameter(queryObj, column);
		}
		return queryObj.list();
	}

	@Override
	@Transactional
	public Integer executeUpdate(StringBuilder query, Map<String, Object> column) throws Exception {
		loggerr.debug("Execute method : executeUpdate()");
		Query queryObj = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
		if (null != column) {
			setQueryUpdateParameter(queryObj, column);
		}
		return queryObj.executeUpdate();
	}

	@Override
	@Transactional
	public Integer executeSqlUpdate(StringBuilder query, Map<String, Object> column) throws Exception {
		loggerr.debug("Execute method : executeSqlUpdate()");
		Query queryObj = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(query.toString());
		if (null != column) {
			setQueryUpdateParameter(queryObj, column);
		}
		return queryObj.executeUpdate();
	}

	/*
	 * @Override
	 * 
	 * @Transactional public int executeCommonSP(String spName, String... param) throws Exception { loggerr.debug("Execute method : executeCommonSP()"); StringBuilder queryStr = new StringBuilder(
	 * "EXEC "); queryStr.append(spName + " "); StringBuilder paramBuilder = new StringBuilder(); if (param != null) { for (String p : param) { if (p != null) { p = p.replaceAll("'", "''"); } if
	 * (paramBuilder.length() > 0) { paramBuilder.append(", "); } if (p != null) { paramBuilder.append("'" + p + "'"); } else { paramBuilder.append("" + p + ""); }
	 * 
	 * } queryStr.append(paramBuilder.toString()); } SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession() .createSQLQuery(queryStr.toString()); return query.executeUpdate();
	 * }
	 */

	@Override
	@Transactional
	public int executeCommonSP(String spName, String... param) throws Exception {
		return executeCommonSPBase(spName, param).executeUpdate();
	}

	@Override
	public List executeCommonSPList(String spName, String... param) throws Exception {
		return executeCommonSPBase(spName, param).list();
	}

	// @Override
	@Transactional
	public SQLQuery executeCommonSPBase(String spName, String... param) throws Exception {
		loggerr.debug("Execute method : executeCommonSP()");
		StringBuilder queryStr = new StringBuilder("EXEC ");
		queryStr.append(spName + " ");
		StringBuilder paramBuilder = new StringBuilder();
		if (param != null) {
			for (String p : param) {
				if (p != null) {
					p = p.replaceAll("'", "''");
				}
				if (paramBuilder.length() > 0) {
					paramBuilder.append(", ");
				}
				if (p != null) {
					paramBuilder.append("'" + p + "'");
				} else {
					paramBuilder.append("" + p + "");
				}

			}
			queryStr.append(paramBuilder.toString());
		}

		return getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(queryStr.toString());
	}

	@Override
	@Transactional
	public void evictObject(final Object object) throws Exception {
		loggerr.debug("Execute method : evictObject()");
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
		getHibernateTemplate().getSessionFactory().getCurrentSession().evict(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getListByRistrictions(Class<T> c, Map<String, Object> eqParam, String[] columnNames, Map<String, Object> others) throws Exception {
		loggerr.debug("Execute method : getListByRistrictions()");
		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(c);
		if (eqParam != null) {
			criteria.add(Restrictions.allEq(eqParam));
		}
		if (others != null) {
			if (others.containsKey("page")) {
				criteria.setFirstResult(Integer.parseInt(others.get("page").toString()));
				criteria.setMaxResults(10);
			}
			if (others.containsKey("maxResults")) {
				criteria.setMaxResults(Integer.parseInt(others.get("maxResults").toString()));
			}
			if (others.containsKey("orderBy")) {
				String orderBy = others.get("orderBy").toString();

				if (orderBy.contains(" desc")) {
					criteria.addOrder(Order.desc(orderBy.replace(" desc", "")));

				} else if (orderBy.contains(" asc")) {
					criteria.addOrder(Order.asc(orderBy.replace(" asc", "")));
				}
			}
		}
		if (columnNames != null && columnNames.length > 0) {
			criteria.setProjection(getProjectionList(columnNames));
			criteria.setResultTransformer(Transformers.aliasToBean(c));
		}
		return criteria.list();
	}

	@Override
	public Criteria createCriteria(Class<T> c, String alias) throws Exception {
		if (!StringUtil.isNullorEmpty(alias)) {
			return getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(c, alias);
		}
		return getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(c);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List executeCriteria(Criteria cr) throws Exception {
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> executeCriteria(Criteria cr, List<QueryParams> queryParamList) throws Exception {
		if (null != queryParamList && !queryParamList.isEmpty()) {
			customRestrictions(queryParamList, cr);
		}
		return cr.list();
	}

	/**
	 * 
	 * @param columnNames
	 * @return projectionList
	 * @author pbhavsar
	 * @ModifiedBy pbhavsar
	 * @ModifiedDate Feb 28, 2017
	 */
	private ProjectionList getProjectionList(String[] columnNames) {
		ProjectionList projectionList = Projections.projectionList();
		for (String column : columnNames) {
			projectionList.add(Projections.property(column), column);
		}
		return projectionList;
	}

	/**
	 * column.put("alias",Payment.class); or column.put("alias","map");
	 * 
	 * For in and not in query pass object array of value map1.put("entityBusinessId", strArray); String str= "90,2"; String strArray[] = str.split(",");
	 * 
	 * if you wish map as return object then pass alias and map, if you want bean as return then pass class name. if you want limited results then pass firstIndex and maxResult in this map, both this
	 * object must be Integer map.put("firstIndex", 0); map.put("maxResult", 10);
	 * 
	 * @author Chandani
	 * @param query
	 * @param column
	 * @return column.put("alias",SAALFlexibleWorkFlow.class); or
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void setQueryParameter(Query query, Map<String, Object> column) throws Exception {
		if (null != column) {
			if (column.containsKey(ALIAS)) {
				query.setResultTransformer(Transformers.aliasToBean((Class<T>) column.get(ALIAS)));
				column.remove(ALIAS);
			}
			if (column.containsKey("addEntity")) {
				((SQLQuery) query).addEntity((Class<T>) column.get("addEntity"));
				column.remove("addEntity");
			}
			if (column.containsKey("firstIndex")) {
				query.setFirstResult((Integer) column.get("firstIndex"));
				column.remove("firstIndex");
			}
			if (column.containsKey("uniqueResult")) {
				query.uniqueResult();
				column.remove("uniqueResult");
			}
			if (column.containsKey(MAXRESULT)) {
				query.setMaxResults((Integer) column.get(MAXRESULT));
				column.remove(MAXRESULT);
			}
			// for IN and NOT-IN query
			for (Map.Entry<String, Object> entity : column.entrySet()) {
				if (entity.getValue() instanceof Object[]) {
					query.setParameterList(entity.getKey(), (Object[]) entity.getValue());
				} else if (entity.getValue() instanceof ArrayList) {
					query.setParameterList(entity.getKey(), (ArrayList) entity.getValue());
				} else {
					query.setParameter(entity.getKey(), entity.getValue());
				}
			}
		}
	}

	/**
	 * 
	 * @author CCJoshi
	 * @param query
	 * @param column
	 * @return
	 */
	private void setQueryUpdateParameter(Query query, Map<String, Object> column) throws Exception {
		for (Map.Entry<String, Object> entity : column.entrySet()) {

			if (entity.getValue() instanceof Collection<?>) {
				query.setParameterList(entity.getKey(), (Collection) entity.getValue());
			} else if (entity.getValue() instanceof Object[]) {
				query.setParameterList(entity.getKey(), (Object[]) entity.getValue());
			} else {
				query.setParameter(entity.getKey(), entity.getValue());
			}
		}
	}

	@Override
	public List<Map<String, Object>> getDataFromSP(String spName, String... param) throws Exception {
		StringBuilder queryStr = new StringBuilder("EXEC ");
		queryStr.append(spName + " ");
		StringBuilder paramBuilder = new StringBuilder();
		if (param != null) {
			for (String p : param) {
				// if (p != null) {
				// p = p.replaceAll("'", "''");
				// }
				if (paramBuilder.length() > 0) {
					paramBuilder.append(", ");
				}
				if (!StringUtil.isNullorEmpty(p)) {
					paramBuilder.append("'" + p + "'");
				} else {
					paramBuilder.append(" null");
				}
			}
			queryStr.append(paramBuilder);
		}

		return executeSqlSelectMap(queryStr, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getListByCriteria(Class<T> c, String[] columnNames, List<QueryParams> queryParamList) throws Exception {

		List<T> resultList;

		loggerr.debug("Execute method : getListByCriteria()");

		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(c);

		if (null != queryParamList && !queryParamList.isEmpty()) {

			customRestrictions(queryParamList, criteria);
		}

		if (columnNames != null && columnNames.length > 0) {
			criteria.setProjection(getProjectionList(columnNames));
			criteria.setResultTransformer(Transformers.aliasToBean(c));
		}
		resultList = criteria.list();

		return resultList;
	}

	private void customRestrictions(List<QueryParams> queryParamList, Criteria criteria) {
		for (QueryParams queryParam : queryParamList) {
			QueryConstants condition = QueryConstants.valueOf(queryParam.getCondition().toString());
			String property = queryParam.getPropertyName().toString();
			Object value = queryParam.getPropertyValue();

			switch (condition) {
			case EQ:
				criteria.add(Restrictions.eq(property, value));
				break;

			case NE:
				criteria.add(Restrictions.ne(property, value));
				break;

			case STARTS_WITH:
				criteria.add(Restrictions.like(property, String.valueOf(value), MatchMode.START));
				break;

			case LIKE:
				criteria.add(Restrictions.like(property, String.valueOf(value), MatchMode.ANYWHERE));
				break;

			case GT:
				criteria.add(Restrictions.gt(property, value));
				break;

			case LT:
				criteria.add(Restrictions.lt(property, value));
				break;

			case GE:
				criteria.add(Restrictions.ge(property, value));
				break;

			case LE:
				criteria.add(Restrictions.le(property, value));
				break;

			case IN:
				criteria.add(Restrictions.in(property, (Object[]) value));
				break;

			case NOT_IN:
				criteria.add(Restrictions.not(Restrictions.in(property, (Object[]) value)));
				break;
			case ISNULL:
				criteria.add(Restrictions.isNull(property));
				break;
			case NOTNULL:
				criteria.add(Restrictions.isNotNull(property));
				break;
			case ORDER_BY:
				orderBy(property, value, criteria);
				break;

			case RESULT_COUNT:
				criteria.setFirstResult(Integer.valueOf(property));
				criteria.setMaxResults((Integer) value);
				break;

			default:
				break;
			}
		}
	}

	private void orderBy(String property, Object orderBy, Criteria criteria) {

		if (QueryConstants.DESC.equals(orderBy)) {
			criteria.addOrder(Order.desc(property));

		} else {
			criteria.addOrder(Order.asc(property));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Class<T> type, Integer id) throws Exception {
		loggerr.debug("Execute method : get()");
		Object o = getHibernateTemplate().load(type, id);
		o = ((HibernateProxy) o).getHibernateLazyInitializer().getImplementation();
		return (T) o;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> returnAsIntegerList(StringBuilder query, Map<String, Object> column) throws Exception {
		loggerr.debug("Execute method : returnAsStringList()");
		Query queryObj = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(query.toString());
		if (null != column) {
			setQueryParameter(queryObj, column);
		}
		return queryObj.list();
	}

	public void executeSqlBatch(List<StringBuilder> queryList) {
		loggerr.debug("Execute method : executeSqlBatch()");
		getHibernateTemplate().getSessionFactory().getCurrentSession().doWork(new Work() {

			@Override
			public void execute(Connection conn) throws SQLException {
				Statement st = null;
				try {
					st = conn.createStatement();
					for (StringBuilder qry : queryList) {
						st.addBatch(qry.toString());
					}
					st.executeBatch();
				} finally {
					if (st != null) {
						st.close();
					}
				}
			}
		});
	}

	public List<BigInteger> geBigIntList(String sqlQuery) {
		loggerr.debug("Execute method : geBigIntList()");
		Query queryObj = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
		List<BigInteger> list = queryObj.list();
		return list;
	}

	/**
	 * added setResultTransformer for getting distinct result.
	 */
	@Override
	public List<T> executeSqlSelectList(StringBuilder query, Map<String, Object> column, Class<T> class1) throws Exception {
		loggerr.debug("Execute method : executeSqlSelect()");
		SQLQuery queryObj = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(query.toString());
		if (null != column) {
			setQueryParameter(queryObj, column);
		}
		queryObj.addEntity(class1);

		// added By Raj Mevada
		queryObj.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		// queryObj.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return queryObj.list();
	}

	/*
	 * @Override public List executeCommonSPList(String spName, String... param) throws Exception { loggerr.debug("Execute method : executeCommonSPList()"); StringBuilder queryStr = new StringBuilder(
	 * "EXEC "); queryStr.append(spName + " "); StringBuilder paramBuilder = new StringBuilder(); if (param != null) { for (String p : param) { if (p != null) { p = p.replaceAll("'", "''"); } if
	 * (paramBuilder.length() > 0) { paramBuilder.append(", "); } if (p != null) { paramBuilder.append("'" + p + "'"); } else { paramBuilder.append("" + p + ""); }
	 * 
	 * } queryStr.append(paramBuilder.toString()); } SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession() .createSQLQuery(queryStr.toString()); return query.list(); }
	 */

	@Override
	@Transactional
	public void evictObject(final Collection<T> objects) throws Exception {
		loggerr.debug("Execute method : evictObject()");
		for (Object bean : objects) {
			getHibernateTemplate().flush();
			getHibernateTemplate().clear();
			getHibernateTemplate().getSessionFactory().getCurrentSession().evict(bean);
		}
	}

}