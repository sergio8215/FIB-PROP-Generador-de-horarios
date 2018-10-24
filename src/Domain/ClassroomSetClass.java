package src.Domain;

import java.util.ArrayList;

public class ClassroomSetClass {

    private ArrayList<ClassroomClass> classroomSet;

    private ArrayList<TheoryClassroomClass> theoryClassroomSet;

    private ArrayList<LabClassroomClass> labClassroomSet;

    public ClassroomSetClass (ArrayList<ClassroomClass> classroomSet){
        this.classroomSet = classroomSet;
        theoryClassroomSet = new ArrayList<>();
        labClassroomSet = new ArrayList<>();
        for (ClassroomClass cc : classroomSet) {
            if (cc.getClass().isAssignableFrom(TheoryClassroomClass.class)) {
                TheoryClassroomClass t = (TheoryClassroomClass) cc;
                theoryClassroomSet.add(t);
            }
            else {
                LabClassroomClass l = (LabClassroomClass) cc;
                labClassroomSet.add(l);
            }
        }
    }

}
