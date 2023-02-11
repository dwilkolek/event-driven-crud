package eu.wilkolek.eventdrivencrud.config;

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.hibernate.type.FormatMapper
import org.hibernate.type.descriptor.WrapperOptions
import org.hibernate.type.descriptor.java.JavaType
import org.hibernate.type.jackson.JacksonJsonFormatMapper

class JacksonJsonFormatMapperCustom : FormatMapper {
    private val delegate: FormatMapper

    init {
        val objectMapper = createObjectMapper()
        delegate = JacksonJsonFormatMapper(objectMapper)
    }

    override fun <T> fromString(charSequence: CharSequence, javaType: JavaType<T>, wrapperOptions: WrapperOptions): T {
        return delegate.fromString(charSequence, javaType, wrapperOptions)
    }

    override fun <T> toString(t: T, javaType: JavaType<T>, wrapperOptions: WrapperOptions): String {
        return delegate.toString(t, javaType, wrapperOptions)
    }

    companion object {
        private fun createObjectMapper(): ObjectMapper {
            return ObjectMapper()
                .registerModule(JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
        }
    }
}