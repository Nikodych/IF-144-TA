package com.softserveinc.ita.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class GroupsDeserializer extends StdDeserializer<GroupEntity> {

    public GroupsDeserializer() {
        this(null);
    }

    public GroupsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GroupEntity deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
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
