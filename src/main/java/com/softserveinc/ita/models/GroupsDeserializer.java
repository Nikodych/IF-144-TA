package com.softserveinc.ita.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class GroupsDeserializer extends StdDeserializer<GroupEntity> {

    public GroupsDeserializer() {
        //Jackson.Databind expects deserializer class to have default constructor
        //Super class StdDeserializer doesn't have default one,
        //so we call one of it's by calling constructor with parameter
        this(null);
    }

    public GroupsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GroupEntity deserialize(JsonParser parser, DeserializationContext context) {
        JsonNode node = null;
        try {
            node = parser.getCodec().readTree(parser);
        } catch (IOException e) {
            throw new AssertionError("Unable to read json with group data");
        }

        var group_id = node.get("group_id").asText();
        var group_name = node.get("group_name").asText();
        var speciality_id = node.get("speciality_id").asText();
        var faculty_id = node.get("faculty_id").asText();

        var speciality = SpecialityEntity
                .builder()
                .id(speciality_id)
                .build();

        var faculty = FacultyEntity
                .builder()
                .id(faculty_id)
                .build();

        return GroupEntity
                .builder()
                .id(group_id)
                .name(group_name)
                .speciality(speciality)
                .faculty(faculty)
                .build();
    }
}
