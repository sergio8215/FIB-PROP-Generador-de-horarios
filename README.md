# FIB-PROP-Schedules-Generador

It is about building an environment for generating hours of different teaching units according to certain conditions previously established. 
The application framework must be as flexible as possible. We can consider a teaching unit (for example the FIB) that has to organize the schedules corresponding to various curriculums and various degrees. 
To begin with, a fixed scenario will be established, which will define the conditions regarding the class period, classrooms available, curriculum and properties of each subject. This scenario implicitly defines specific constraints and can also be explicitly defined by others. 

At least the following types of restriction should be considered: 
organization by level of the syllabus (not overlapping subjects of the same level), correc- tures between subjects, the number, type and duration of the sessions for Each subject, the lecture period and the number of classrooms (and capacity) necessary for each subject (depending on their groups). The system must allow the preparation of a schedule according to the restrictions indicated by the user, taking into account that: 
      
    •  There may not be a solution for a user's request. In this case the user will have to eliminate (or soften) any (s) of the restrictions so that there is a solution. 
    •  The expressive wealth of the application's restrictions will be positively valued. 
    •  The data must be able to be defined via the program or imported from a text file. 
    •  The system must allow subsequent modification of the proposed solution (maintaining consistency). 
    •  In addition to the other quality factors of any program (design, coding, reusability, modifiability, usability, documentation ...), the efficiency and flexibility of this one will be valued in particular. 
    •   As the extension may be considered the possibility that certain restrictions are mere preferences
