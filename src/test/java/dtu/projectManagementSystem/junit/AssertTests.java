package dtu.projectManagementSystem.junit;

import dtu.projectManagementSystem.app.SoftwareHuset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertTests {

    private final SoftwareHuset softwareHuset = new SoftwareHuset();

    @Test
    public void testCreateProjectFailingAsserts()  {
        //Start by calling the method
        try {
            softwareHuset.createProject("assertTest");
        } catch (Exception e) {
            System.out.println(e);
        }
        //now I print all projects to see if a project named "assertTest" is there.
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < softwareHuset.getProjectRepository().size(); i++) {
            s.append(softwareHuset.getProjectRepository().get(i).getProjectName()).append("\n");
        }
        System.out.println(s.toString());

        /* Theis will automatically tell me if "assertTest" is there,
         * in case the list of projects is really long, and not quickly
         * skimmed with my eyes
         */
        if((softwareHuset.getProjectRepository().stream().
                anyMatch(i -> i.getProjectName().equals("assertTest")))){
            System.out.println("assertTest is in the list..");
        }

    }
}
