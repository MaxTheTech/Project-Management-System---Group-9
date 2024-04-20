package dtu.projectManagementSystem.acceptance_tests.helper;

import dtu.projectManagementSystem.domain.Project;

import java.util.HashMap;

public class ProjectHelper {

    private static HashMap<String,Integer> projectParameterExamples = new HashMap<String, Integer>() {
        {
            put("startingWeek1",1);
            put("startingWeek2",2);

            put("duration1",1);
            put("duration2",2);

            put("expectedWorkload1",100);
            put("expectedWorkload2",200);
        }
    };

    public static HashMap<String,Integer> getProjectParameterExamples(){
        return projectParameterExamples;
    }


}
