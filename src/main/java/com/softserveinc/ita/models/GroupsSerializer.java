package com.softserveinc.ita.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class GroupsSerializer extends StdSerializer<GroupEntity> {

    public GroupsSerializer() {
        //Jackson.Databind expects serializer class to have default constructor
        //Super class StdSerializer doesn't have default one,
        //so we call one of it's by calling constructor with parameter
        this(null);
    }

    public GroupsSerializer(Class<GroupEntity> t) {
        super(t);
    }

    @Override
    public void serialize(GroupEntity group, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        try {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("group_name", group.getName());
            jsonGenerator.writeStringField("speciality_id", group.getSpeciality().getId());
            jsonGenerator.writeStringField("faculty_id", group.getFaculty().getId());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new AssertionError("Unable to serialize group");
        }
    }
}
