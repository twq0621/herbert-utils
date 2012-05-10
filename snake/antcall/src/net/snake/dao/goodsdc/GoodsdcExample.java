package net.snake.dao.goodsdc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsdcExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table t_goods_dc
	 * @ibatorgenerated  Thu May 05 17:53:42 CST 2011
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table t_goods_dc
	 * @ibatorgenerated  Thu May 05 17:53:42 CST 2011
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_goods_dc
	 * @ibatorgenerated  Thu May 05 17:53:42 CST 2011
	 */
	public GoodsdcExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_goods_dc
	 * @ibatorgenerated  Thu May 05 17:53:42 CST 2011
	 */
	protected GoodsdcExample(GoodsdcExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_goods_dc
	 * @ibatorgenerated  Thu May 05 17:53:42 CST 2011
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_goods_dc
	 * @ibatorgenerated  Thu May 05 17:53:42 CST 2011
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_goods_dc
	 * @ibatorgenerated  Thu May 05 17:53:42 CST 2011
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_goods_dc
	 * @ibatorgenerated  Thu May 05 17:53:42 CST 2011
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_goods_dc
	 * @ibatorgenerated  Thu May 05 17:53:42 CST 2011
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_goods_dc
	 * @ibatorgenerated  Thu May 05 17:53:42 CST 2011
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_goods_dc
	 * @ibatorgenerated  Thu May 05 17:53:42 CST 2011
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table t_goods_dc
	 * @ibatorgenerated  Thu May 05 17:53:42 CST 2011
	 */
	public static class Criteria {
		protected List criteriaWithoutValue;
		protected List criteriaWithSingleValue;
		protected List criteriaWithListValue;
		protected List criteriaWithBetweenValue;
		protected Criteria() {
			super();
			criteriaWithoutValue = new ArrayList();
			criteriaWithSingleValue = new ArrayList();
			criteriaWithListValue = new ArrayList();
			criteriaWithBetweenValue = new ArrayList();
		}
		public boolean isValid() {
			return criteriaWithoutValue.size() > 0 || criteriaWithSingleValue.size() > 0
					|| criteriaWithListValue.size() > 0 || criteriaWithBetweenValue.size() > 0;
		}
		public List getCriteriaWithoutValue() {
			return criteriaWithoutValue;
		}
		public List getCriteriaWithSingleValue() {
			return criteriaWithSingleValue;
		}
		public List getCriteriaWithListValue() {
			return criteriaWithListValue;
		}
		public List getCriteriaWithBetweenValue() {
			return criteriaWithBetweenValue;
		}
		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteriaWithoutValue.add(condition);
		}
		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("value", value);
			criteriaWithSingleValue.add(map);
		}
		protected void addCriterion(String condition, List values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property + " cannot be null or empty");
			}
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("values", values);
			criteriaWithListValue.add(map);
		}
		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			List list = new ArrayList();
			list.add(value1);
			list.add(value2);
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("values", list);
			criteriaWithBetweenValue.add(map);
		}
		public Criteria andIdIsNull() {
			addCriterion("f_id is null");
			return this;
		}
		public Criteria andIdIsNotNull() {
			addCriterion("f_id is not null");
			return this;
		}
		public Criteria andIdEqualTo(Integer value) {
			addCriterion("f_id =", value, "id");
			return this;
		}
		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("f_id <>", value, "id");
			return this;
		}
		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("f_id >", value, "id");
			return this;
		}
		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("f_id >=", value, "id");
			return this;
		}
		public Criteria andIdLessThan(Integer value) {
			addCriterion("f_id <", value, "id");
			return this;
		}
		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("f_id <=", value, "id");
			return this;
		}
		public Criteria andIdIn(List values) {
			addCriterion("f_id in", values, "id");
			return this;
		}
		public Criteria andIdNotIn(List values) {
			addCriterion("f_id not in", values, "id");
			return this;
		}
		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("f_id between", value1, value2, "id");
			return this;
		}
		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("f_id not between", value1, value2, "id");
			return this;
		}
		public Criteria andGradeIsNull() {
			addCriterion("f_grade is null");
			return this;
		}
		public Criteria andGradeIsNotNull() {
			addCriterion("f_grade is not null");
			return this;
		}
		public Criteria andGradeEqualTo(Integer value) {
			addCriterion("f_grade =", value, "grade");
			return this;
		}
		public Criteria andGradeNotEqualTo(Integer value) {
			addCriterion("f_grade <>", value, "grade");
			return this;
		}
		public Criteria andGradeGreaterThan(Integer value) {
			addCriterion("f_grade >", value, "grade");
			return this;
		}
		public Criteria andGradeGreaterThanOrEqualTo(Integer value) {
			addCriterion("f_grade >=", value, "grade");
			return this;
		}
		public Criteria andGradeLessThan(Integer value) {
			addCriterion("f_grade <", value, "grade");
			return this;
		}
		public Criteria andGradeLessThanOrEqualTo(Integer value) {
			addCriterion("f_grade <=", value, "grade");
			return this;
		}
		public Criteria andGradeIn(List values) {
			addCriterion("f_grade in", values, "grade");
			return this;
		}
		public Criteria andGradeNotIn(List values) {
			addCriterion("f_grade not in", values, "grade");
			return this;
		}
		public Criteria andGradeBetween(Integer value1, Integer value2) {
			addCriterion("f_grade between", value1, value2, "grade");
			return this;
		}
		public Criteria andGradeNotBetween(Integer value1, Integer value2) {
			addCriterion("f_grade not between", value1, value2, "grade");
			return this;
		}
		public Criteria andNameIsNull() {
			addCriterion("f_name is null");
			return this;
		}
		public Criteria andNameIsNotNull() {
			addCriterion("f_name is not null");
			return this;
		}
		public Criteria andNameEqualTo(String value) {
			addCriterion("f_name =", value, "name");
			return this;
		}
		public Criteria andNameNotEqualTo(String value) {
			addCriterion("f_name <>", value, "name");
			return this;
		}
		public Criteria andNameGreaterThan(String value) {
			addCriterion("f_name >", value, "name");
			return this;
		}
		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("f_name >=", value, "name");
			return this;
		}
		public Criteria andNameLessThan(String value) {
			addCriterion("f_name <", value, "name");
			return this;
		}
		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("f_name <=", value, "name");
			return this;
		}
		public Criteria andNameLike(String value) {
			addCriterion("f_name like", value, "name");
			return this;
		}
		public Criteria andNameNotLike(String value) {
			addCriterion("f_name not like", value, "name");
			return this;
		}
		public Criteria andNameIn(List values) {
			addCriterion("f_name in", values, "name");
			return this;
		}
		public Criteria andNameNotIn(List values) {
			addCriterion("f_name not in", values, "name");
			return this;
		}
		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("f_name between", value1, value2, "name");
			return this;
		}
		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("f_name not between", value1, value2, "name");
			return this;
		}
		public Criteria andTargetgoodsIsNull() {
			addCriterion("f_targetGoods is null");
			return this;
		}
		public Criteria andTargetgoodsIsNotNull() {
			addCriterion("f_targetGoods is not null");
			return this;
		}
		public Criteria andTargetgoodsEqualTo(String value) {
			addCriterion("f_targetGoods =", value, "targetgoods");
			return this;
		}
		public Criteria andTargetgoodsNotEqualTo(String value) {
			addCriterion("f_targetGoods <>", value, "targetgoods");
			return this;
		}
		public Criteria andTargetgoodsGreaterThan(String value) {
			addCriterion("f_targetGoods >", value, "targetgoods");
			return this;
		}
		public Criteria andTargetgoodsGreaterThanOrEqualTo(String value) {
			addCriterion("f_targetGoods >=", value, "targetgoods");
			return this;
		}
		public Criteria andTargetgoodsLessThan(String value) {
			addCriterion("f_targetGoods <", value, "targetgoods");
			return this;
		}
		public Criteria andTargetgoodsLessThanOrEqualTo(String value) {
			addCriterion("f_targetGoods <=", value, "targetgoods");
			return this;
		}
		public Criteria andTargetgoodsLike(String value) {
			addCriterion("f_targetGoods like", value, "targetgoods");
			return this;
		}
		public Criteria andTargetgoodsNotLike(String value) {
			addCriterion("f_targetGoods not like", value, "targetgoods");
			return this;
		}
		public Criteria andTargetgoodsIn(List values) {
			addCriterion("f_targetGoods in", values, "targetgoods");
			return this;
		}
		public Criteria andTargetgoodsNotIn(List values) {
			addCriterion("f_targetGoods not in", values, "targetgoods");
			return this;
		}
		public Criteria andTargetgoodsBetween(String value1, String value2) {
			addCriterion("f_targetGoods between", value1, value2, "targetgoods");
			return this;
		}
		public Criteria andTargetgoodsNotBetween(String value1, String value2) {
			addCriterion("f_targetGoods not between", value1, value2, "targetgoods");
			return this;
		}
		public Criteria andRewarddescIsNull() {
			addCriterion("f_rewardDesc is null");
			return this;
		}
		public Criteria andRewarddescIsNotNull() {
			addCriterion("f_rewardDesc is not null");
			return this;
		}
		public Criteria andRewarddescEqualTo(String value) {
			addCriterion("f_rewardDesc =", value, "rewarddesc");
			return this;
		}
		public Criteria andRewarddescNotEqualTo(String value) {
			addCriterion("f_rewardDesc <>", value, "rewarddesc");
			return this;
		}
		public Criteria andRewarddescGreaterThan(String value) {
			addCriterion("f_rewardDesc >", value, "rewarddesc");
			return this;
		}
		public Criteria andRewarddescGreaterThanOrEqualTo(String value) {
			addCriterion("f_rewardDesc >=", value, "rewarddesc");
			return this;
		}
		public Criteria andRewarddescLessThan(String value) {
			addCriterion("f_rewardDesc <", value, "rewarddesc");
			return this;
		}
		public Criteria andRewarddescLessThanOrEqualTo(String value) {
			addCriterion("f_rewardDesc <=", value, "rewarddesc");
			return this;
		}
		public Criteria andRewarddescLike(String value) {
			addCriterion("f_rewardDesc like", value, "rewarddesc");
			return this;
		}
		public Criteria andRewarddescNotLike(String value) {
			addCriterion("f_rewardDesc not like", value, "rewarddesc");
			return this;
		}
		public Criteria andRewarddescIn(List values) {
			addCriterion("f_rewardDesc in", values, "rewarddesc");
			return this;
		}
		public Criteria andRewarddescNotIn(List values) {
			addCriterion("f_rewardDesc not in", values, "rewarddesc");
			return this;
		}
		public Criteria andRewarddescBetween(String value1, String value2) {
			addCriterion("f_rewardDesc between", value1, value2, "rewarddesc");
			return this;
		}
		public Criteria andRewarddescNotBetween(String value1, String value2) {
			addCriterion("f_rewardDesc not between", value1, value2, "rewarddesc");
			return this;
		}
		public Criteria andPointcntIsNull() {
			addCriterion("f_pointCnt is null");
			return this;
		}
		public Criteria andPointcntIsNotNull() {
			addCriterion("f_pointCnt is not null");
			return this;
		}
		public Criteria andPointcntEqualTo(Integer value) {
			addCriterion("f_pointCnt =", value, "pointcnt");
			return this;
		}
		public Criteria andPointcntNotEqualTo(Integer value) {
			addCriterion("f_pointCnt <>", value, "pointcnt");
			return this;
		}
		public Criteria andPointcntGreaterThan(Integer value) {
			addCriterion("f_pointCnt >", value, "pointcnt");
			return this;
		}
		public Criteria andPointcntGreaterThanOrEqualTo(Integer value) {
			addCriterion("f_pointCnt >=", value, "pointcnt");
			return this;
		}
		public Criteria andPointcntLessThan(Integer value) {
			addCriterion("f_pointCnt <", value, "pointcnt");
			return this;
		}
		public Criteria andPointcntLessThanOrEqualTo(Integer value) {
			addCriterion("f_pointCnt <=", value, "pointcnt");
			return this;
		}
		public Criteria andPointcntIn(List values) {
			addCriterion("f_pointCnt in", values, "pointcnt");
			return this;
		}
		public Criteria andPointcntNotIn(List values) {
			addCriterion("f_pointCnt not in", values, "pointcnt");
			return this;
		}
		public Criteria andPointcntBetween(Integer value1, Integer value2) {
			addCriterion("f_pointCnt between", value1, value2, "pointcnt");
			return this;
		}
		public Criteria andPointcntNotBetween(Integer value1, Integer value2) {
			addCriterion("f_pointCnt not between", value1, value2, "pointcnt");
			return this;
		}
		public Criteria andNameI18nIsNull() {
			addCriterion("f_name_i18n is null");
			return this;
		}
		public Criteria andNameI18nIsNotNull() {
			addCriterion("f_name_i18n is not null");
			return this;
		}
		public Criteria andNameI18nEqualTo(String value) {
			addCriterion("f_name_i18n =", value, "nameI18n");
			return this;
		}
		public Criteria andNameI18nNotEqualTo(String value) {
			addCriterion("f_name_i18n <>", value, "nameI18n");
			return this;
		}
		public Criteria andNameI18nGreaterThan(String value) {
			addCriterion("f_name_i18n >", value, "nameI18n");
			return this;
		}
		public Criteria andNameI18nGreaterThanOrEqualTo(String value) {
			addCriterion("f_name_i18n >=", value, "nameI18n");
			return this;
		}
		public Criteria andNameI18nLessThan(String value) {
			addCriterion("f_name_i18n <", value, "nameI18n");
			return this;
		}
		public Criteria andNameI18nLessThanOrEqualTo(String value) {
			addCriterion("f_name_i18n <=", value, "nameI18n");
			return this;
		}
		public Criteria andNameI18nLike(String value) {
			addCriterion("f_name_i18n like", value, "nameI18n");
			return this;
		}
		public Criteria andNameI18nNotLike(String value) {
			addCriterion("f_name_i18n not like", value, "nameI18n");
			return this;
		}
		public Criteria andNameI18nIn(List values) {
			addCriterion("f_name_i18n in", values, "nameI18n");
			return this;
		}
		public Criteria andNameI18nNotIn(List values) {
			addCriterion("f_name_i18n not in", values, "nameI18n");
			return this;
		}
		public Criteria andNameI18nBetween(String value1, String value2) {
			addCriterion("f_name_i18n between", value1, value2, "nameI18n");
			return this;
		}
		public Criteria andNameI18nNotBetween(String value1, String value2) {
			addCriterion("f_name_i18n not between", value1, value2, "nameI18n");
			return this;
		}
		public Criteria andRewarddescI18nIsNull() {
			addCriterion("f_rewardDesc_i18n is null");
			return this;
		}
		public Criteria andRewarddescI18nIsNotNull() {
			addCriterion("f_rewardDesc_i18n is not null");
			return this;
		}
		public Criteria andRewarddescI18nEqualTo(String value) {
			addCriterion("f_rewardDesc_i18n =", value, "rewarddescI18n");
			return this;
		}
		public Criteria andRewarddescI18nNotEqualTo(String value) {
			addCriterion("f_rewardDesc_i18n <>", value, "rewarddescI18n");
			return this;
		}
		public Criteria andRewarddescI18nGreaterThan(String value) {
			addCriterion("f_rewardDesc_i18n >", value, "rewarddescI18n");
			return this;
		}
		public Criteria andRewarddescI18nGreaterThanOrEqualTo(String value) {
			addCriterion("f_rewardDesc_i18n >=", value, "rewarddescI18n");
			return this;
		}
		public Criteria andRewarddescI18nLessThan(String value) {
			addCriterion("f_rewardDesc_i18n <", value, "rewarddescI18n");
			return this;
		}
		public Criteria andRewarddescI18nLessThanOrEqualTo(String value) {
			addCriterion("f_rewardDesc_i18n <=", value, "rewarddescI18n");
			return this;
		}
		public Criteria andRewarddescI18nLike(String value) {
			addCriterion("f_rewardDesc_i18n like", value, "rewarddescI18n");
			return this;
		}
		public Criteria andRewarddescI18nNotLike(String value) {
			addCriterion("f_rewardDesc_i18n not like", value, "rewarddescI18n");
			return this;
		}
		public Criteria andRewarddescI18nIn(List values) {
			addCriterion("f_rewardDesc_i18n in", values, "rewarddescI18n");
			return this;
		}
		public Criteria andRewarddescI18nNotIn(List values) {
			addCriterion("f_rewardDesc_i18n not in", values, "rewarddescI18n");
			return this;
		}
		public Criteria andRewarddescI18nBetween(String value1, String value2) {
			addCriterion("f_rewardDesc_i18n between", value1, value2, "rewarddescI18n");
			return this;
		}
		public Criteria andRewarddescI18nNotBetween(String value1, String value2) {
			addCriterion("f_rewardDesc_i18n not between", value1, value2, "rewarddescI18n");
			return this;
		}
	}
}