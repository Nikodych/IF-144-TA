package com.softserveinc.ita.apisteps;

import com.softserveinc.ita.models.FacultyEntity;
import com.softserveinc.ita.models.GroupEntity;
import com.softserveinc.ita.models.SpecialityEntity;
import io.restassured.http.Cookie;
import lombok.AllArgsConstructor;

import static com.softserveinc.ita.util.ApiUtil.createGroup;
import static com.softserveinc.ita.util.ApiUtil.getGroupsListByAPI;
import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class GroupsApiStep {

    private Cookie sessionId;

    public GroupEntity createNewGroup(SpecialityEntity speciality, FacultyEntity faculty) {
        var group = GroupEntity.getNewValidGroup(speciality, faculty);
        var responseGroup = createGroup(group, sessionId);
        assertThat(responseGroup)
                .as("After adding group with POST API it should be returned in response")
                .hasSize(1)
                .contains(group);

        group = responseGroup.get(0); // in order to get known given id
        return group;
    }

    public GroupEntity findGroup(GroupEntity group) {
        var groupsList = getGroupsListByAPI(sessionId);
        assertThat(groupsList)
                .as("List of groups returned by API should contain group")
                .contains(group);
        //in order to get known given id
        return groupsList.get(groupsList.indexOf(group));
    }

    public void verifyGroupNotExist(GroupEntity group) {
        var groupsList = getGroupsListByAPI(sessionId);
        assertThat(groupsList)
                .as("List of groups returned by API shouldn't contain group")
                .doesNotContain(group);
    }
}
