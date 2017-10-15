package com.coonchen.core.entity;

import java.util.ArrayList;
import java.util.List;

public class UserGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserGroupExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSugidIsNull() {
            addCriterion("sugid is null");
            return (Criteria) this;
        }

        public Criteria andSugidIsNotNull() {
            addCriterion("sugid is not null");
            return (Criteria) this;
        }

        public Criteria andSugidEqualTo(Integer value) {
            addCriterion("sugid =", value, "sugid");
            return (Criteria) this;
        }

        public Criteria andSugidNotEqualTo(Integer value) {
            addCriterion("sugid <>", value, "sugid");
            return (Criteria) this;
        }

        public Criteria andSugidGreaterThan(Integer value) {
            addCriterion("sugid >", value, "sugid");
            return (Criteria) this;
        }

        public Criteria andSugidGreaterThanOrEqualTo(Integer value) {
            addCriterion("sugid >=", value, "sugid");
            return (Criteria) this;
        }

        public Criteria andSugidLessThan(Integer value) {
            addCriterion("sugid <", value, "sugid");
            return (Criteria) this;
        }

        public Criteria andSugidLessThanOrEqualTo(Integer value) {
            addCriterion("sugid <=", value, "sugid");
            return (Criteria) this;
        }

        public Criteria andSugidIn(List<Integer> values) {
            addCriterion("sugid in", values, "sugid");
            return (Criteria) this;
        }

        public Criteria andSugidNotIn(List<Integer> values) {
            addCriterion("sugid not in", values, "sugid");
            return (Criteria) this;
        }

        public Criteria andSugidBetween(Integer value1, Integer value2) {
            addCriterion("sugid between", value1, value2, "sugid");
            return (Criteria) this;
        }

        public Criteria andSugidNotBetween(Integer value1, Integer value2) {
            addCriterion("sugid not between", value1, value2, "sugid");
            return (Criteria) this;
        }

        public Criteria andSugnameIsNull() {
            addCriterion("sugname is null");
            return (Criteria) this;
        }

        public Criteria andSugnameIsNotNull() {
            addCriterion("sugname is not null");
            return (Criteria) this;
        }

        public Criteria andSugnameEqualTo(String value) {
            addCriterion("sugname =", value, "sugname");
            return (Criteria) this;
        }

        public Criteria andSugnameNotEqualTo(String value) {
            addCriterion("sugname <>", value, "sugname");
            return (Criteria) this;
        }

        public Criteria andSugnameGreaterThan(String value) {
            addCriterion("sugname >", value, "sugname");
            return (Criteria) this;
        }

        public Criteria andSugnameGreaterThanOrEqualTo(String value) {
            addCriterion("sugname >=", value, "sugname");
            return (Criteria) this;
        }

        public Criteria andSugnameLessThan(String value) {
            addCriterion("sugname <", value, "sugname");
            return (Criteria) this;
        }

        public Criteria andSugnameLessThanOrEqualTo(String value) {
            addCriterion("sugname <=", value, "sugname");
            return (Criteria) this;
        }

        public Criteria andSugnameLike(String value) {
            addCriterion("sugname like", value, "sugname");
            return (Criteria) this;
        }

        public Criteria andSugnameNotLike(String value) {
            addCriterion("sugname not like", value, "sugname");
            return (Criteria) this;
        }

        public Criteria andSugnameIn(List<String> values) {
            addCriterion("sugname in", values, "sugname");
            return (Criteria) this;
        }

        public Criteria andSugnameNotIn(List<String> values) {
            addCriterion("sugname not in", values, "sugname");
            return (Criteria) this;
        }

        public Criteria andSugnameBetween(String value1, String value2) {
            addCriterion("sugname between", value1, value2, "sugname");
            return (Criteria) this;
        }

        public Criteria andSugnameNotBetween(String value1, String value2) {
            addCriterion("sugname not between", value1, value2, "sugname");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}