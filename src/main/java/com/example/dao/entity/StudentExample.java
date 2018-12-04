package com.example.dao.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

public class StudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    private Boolean forUpdate;

    public StudentExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    public void setForUpdate(Boolean forUpdate) {
        this.forUpdate = forUpdate;
    }

    public Boolean getForUpdate() {
        return forUpdate;
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

        public Criteria andSNOIsNull() {
            addCriterion("student.SNO is null");
            return (Criteria) this;
        }

        public Criteria andSNOIsNotNull() {
            addCriterion("student.SNO is not null");
            return (Criteria) this;
        }

        public Criteria andSNOEqualTo(String value) {
            addCriterion("student.SNO =", value, "SNO");
            return (Criteria) this;
        }

        public Criteria andSNONotEqualTo(String value) {
            addCriterion("student.SNO <>", value, "SNO");
            return (Criteria) this;
        }

        public Criteria andSNOGreaterThan(String value) {
            addCriterion("student.SNO >", value, "SNO");
            return (Criteria) this;
        }

        public Criteria andSNOGreaterThanOrEqualTo(String value) {
            addCriterion("student.SNO >=", value, "SNO");
            return (Criteria) this;
        }

        public Criteria andSNOLessThan(String value) {
            addCriterion("student.SNO <", value, "SNO");
            return (Criteria) this;
        }

        public Criteria andSNOLessThanOrEqualTo(String value) {
            addCriterion("student.SNO <=", value, "SNO");
            return (Criteria) this;
        }

        public Criteria andSNOLike(String value) {
            addCriterion("student.SNO like", value, "SNO");
            return (Criteria) this;
        }

        public Criteria andSNONotLike(String value) {
            addCriterion("student.SNO not like", value, "SNO");
            return (Criteria) this;
        }

        public Criteria andSNOIn(List<String> values) {
            addCriterion("student.SNO in", values, "SNO");
            return (Criteria) this;
        }

        public Criteria andSNONotIn(List<String> values) {
            addCriterion("student.SNO not in", values, "SNO");
            return (Criteria) this;
        }

        public Criteria andSNOBetween(String value1, String value2) {
            addCriterion("student.SNO between", value1, value2, "SNO");
            return (Criteria) this;
        }

        public Criteria andSNONotBetween(String value1, String value2) {
            addCriterion("student.SNO not between", value1, value2, "SNO");
            return (Criteria) this;
        }

        public Criteria andSNAMEIsNull() {
            addCriterion("student.SNAME is null");
            return (Criteria) this;
        }

        public Criteria andSNAMEIsNotNull() {
            addCriterion("student.SNAME is not null");
            return (Criteria) this;
        }

        public Criteria andSNAMEEqualTo(String value) {
            addCriterion("student.SNAME =", value, "SNAME");
            return (Criteria) this;
        }

        public Criteria andSNAMENotEqualTo(String value) {
            addCriterion("student.SNAME <>", value, "SNAME");
            return (Criteria) this;
        }

        public Criteria andSNAMEGreaterThan(String value) {
            addCriterion("student.SNAME >", value, "SNAME");
            return (Criteria) this;
        }

        public Criteria andSNAMEGreaterThanOrEqualTo(String value) {
            addCriterion("student.SNAME >=", value, "SNAME");
            return (Criteria) this;
        }

        public Criteria andSNAMELessThan(String value) {
            addCriterion("student.SNAME <", value, "SNAME");
            return (Criteria) this;
        }

        public Criteria andSNAMELessThanOrEqualTo(String value) {
            addCriterion("student.SNAME <=", value, "SNAME");
            return (Criteria) this;
        }

        public Criteria andSNAMELike(String value) {
            addCriterion("student.SNAME like", value, "SNAME");
            return (Criteria) this;
        }

        public Criteria andSNAMENotLike(String value) {
            addCriterion("student.SNAME not like", value, "SNAME");
            return (Criteria) this;
        }

        public Criteria andSNAMEIn(List<String> values) {
            addCriterion("student.SNAME in", values, "SNAME");
            return (Criteria) this;
        }

        public Criteria andSNAMENotIn(List<String> values) {
            addCriterion("student.SNAME not in", values, "SNAME");
            return (Criteria) this;
        }

        public Criteria andSNAMEBetween(String value1, String value2) {
            addCriterion("student.SNAME between", value1, value2, "SNAME");
            return (Criteria) this;
        }

        public Criteria andSNAMENotBetween(String value1, String value2) {
            addCriterion("student.SNAME not between", value1, value2, "SNAME");
            return (Criteria) this;
        }

        public Criteria andSSEXIsNull() {
            addCriterion("student.SSEX is null");
            return (Criteria) this;
        }

        public Criteria andSSEXIsNotNull() {
            addCriterion("student.SSEX is not null");
            return (Criteria) this;
        }

        public Criteria andSSEXEqualTo(String value) {
            addCriterion("student.SSEX =", value, "SSEX");
            return (Criteria) this;
        }

        public Criteria andSSEXNotEqualTo(String value) {
            addCriterion("student.SSEX <>", value, "SSEX");
            return (Criteria) this;
        }

        public Criteria andSSEXGreaterThan(String value) {
            addCriterion("student.SSEX >", value, "SSEX");
            return (Criteria) this;
        }

        public Criteria andSSEXGreaterThanOrEqualTo(String value) {
            addCriterion("student.SSEX >=", value, "SSEX");
            return (Criteria) this;
        }

        public Criteria andSSEXLessThan(String value) {
            addCriterion("student.SSEX <", value, "SSEX");
            return (Criteria) this;
        }

        public Criteria andSSEXLessThanOrEqualTo(String value) {
            addCriterion("student.SSEX <=", value, "SSEX");
            return (Criteria) this;
        }

        public Criteria andSSEXLike(String value) {
            addCriterion("student.SSEX like", value, "SSEX");
            return (Criteria) this;
        }

        public Criteria andSSEXNotLike(String value) {
            addCriterion("student.SSEX not like", value, "SSEX");
            return (Criteria) this;
        }

        public Criteria andSSEXIn(List<String> values) {
            addCriterion("student.SSEX in", values, "SSEX");
            return (Criteria) this;
        }

        public Criteria andSSEXNotIn(List<String> values) {
            addCriterion("student.SSEX not in", values, "SSEX");
            return (Criteria) this;
        }

        public Criteria andSSEXBetween(String value1, String value2) {
            addCriterion("student.SSEX between", value1, value2, "SSEX");
            return (Criteria) this;
        }

        public Criteria andSSEXNotBetween(String value1, String value2) {
            addCriterion("student.SSEX not between", value1, value2, "SSEX");
            return (Criteria) this;
        }
    }

    /**
     */
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