package ca.ulaval.glo.model

import org.apache.commons.lang3.builder.*

abstract class ValueObject : Comparable<Any> {
    override fun equals(other: Any?): Boolean {
        return EqualsBuilder.reflectionEquals(this, other)
    }

    override fun hashCode(): Int {
        return HashCodeBuilder.reflectionHashCode(this)
    }

    override fun compareTo(other: Any): Int {
        return CompareToBuilder.reflectionCompare(this, other)
    }

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE)
    }
}
