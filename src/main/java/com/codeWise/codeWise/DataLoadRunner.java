package com.codeWise.codeWise;

import com.codeWise.codeWise.model.*;
import com.codeWise.codeWise.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoadRunner implements CommandLineRunner {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private StudentResourceRepository studentResourceRepository;

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ValutationRepository valutationRepository;

    @Override
    public void run(String... args) throws Exception {

        valutationRepository.deleteAll();
        paperRepository.deleteAll();
        studentResourceRepository.deleteAll();
        noteRepository.deleteAll();
        attachmentRepository.deleteAll();
        exerciseRepository.deleteAll();
        resourceRepository.deleteAll();
        teacherCourseRepository.deleteAll();
        studentRepository.deleteAll();
        teacherRepository.deleteAll();
        courseRepository.deleteAll();

        // ==== CREAZIONE TEACHER ====
        Teacher t1 = new Teacher("Mario", "Rossi", "mario.rossi@uni.it", "Professore");
        Teacher t2 = new Teacher("Luca", "Verdi", "luca.verdi@uni.it", "Ricercatore");
        Teacher t3 = new Teacher("Anna", "Bianchi", "anna.bianchi@uni.it", "Prof.ssa");
        Teacher t4 = new Teacher("Paola", "Neri", "paola.neri@uni.it", "Assistente");
        teacherRepository.save(t1);
        teacherRepository.save(t2);
        teacherRepository.save(t3);
        teacherRepository.save(t4);

        // ==== CREAZIONE CORSI ====
        Course c1 = new Course("Matematica", 2024, 9);
        Course c2 = new Course("Fisica", 2024, 6);
        Course c3 = new Course("Informatica", 2024, 12);
        Course c4 = new Course("Chimica", 2024, 6);
        courseRepository.save(c1);
        courseRepository.save(c2);
        courseRepository.save(c3);
        courseRepository.save(c4);

        // ==== ASSOCIAZIONE TEACHER-COURSE ====
        teacherCourseRepository.save(new TeacherCourse(t1, c1));
        teacherCourseRepository.save(new TeacherCourse(t2, c2));
        teacherCourseRepository.save(new TeacherCourse(t3, c3));
        teacherCourseRepository.save(new TeacherCourse(t4, c4));

        // ==== CREAZIONE STUDENTI ====
        List<Student> allStudents = new ArrayList<>();
        String[][] studentData = {
                {"Alessandro", "Moretti", "alessandro.moretti@uni.it"},
                {"Chiara", "Rinaldi", "chiara.rinaldi@uni.it"},
                {"Davide", "Gallo", "davide.gallo@uni.it"},
                {"Francesca", "Romano", "francesca.romano@uni.it"},
                {"Simone", "Conti", "simone.conti@uni.it"},

                {"Elena", "Ferrari", "elena.ferrari@uni.it"},
                {"Andrea", "Mariani", "andrea.mariani@uni.it"},
                {"Serena", "Lombardi", "serena.lombardi@uni.it"},
                {"Matteo", "Costa", "matteo.costa@uni.it"},
                {"Valeria", "Fontana", "valeria.fontana@uni.it"},

                {"Federico", "De Luca", "federico.deluca@uni.it"},
                {"Ilaria", "Serra", "ilaria.serra@uni.it"},
                {"Lorenzo", "Coppola", "lorenzo.coppola@uni.it"},
                {"Marta", "Palumbo", "marta.palumbo@uni.it"},
                {"Giorgio", "Martini", "giorgio.martini@uni.it"},

                {"Paola", "Vitale", "paola.vitale@uni.it"},
                {"Riccardo", "Fiore", "riccardo.fiore@uni.it"},
                {"Sara", "Mancini", "sara.mancini@uni.it"},
                {"Tommaso", "Basile", "tommaso.basile@uni.it"},
                {"Greta", "Rizzi", "greta.rizzi@uni.it"},
        };

        for (int i = 0; i < studentData.length; i++) {
            Student s = new Student(
                    studentData[i][0],
                    studentData[i][1],
                    studentData[i][2],
                    LocalDate.of(2000, 1, 10).plusDays(i * 5),
                    "Milano",
                    2023
            );
            // Assign course (5 per course)
            if (i < 5) s.setCourse(c1);
            else if (i < 10) s.setCourse(c2);
            else if (i < 15) s.setCourse(c3);
            else s.setCourse(c4);
            allStudents.add(s);
        }

        studentRepository.saveAll(allStudents);

        // ==== CREAZIONE EXERCISE (ESAMI) ====
        List<Exercise> allExercises = List.of(
                new Exercise(LocalDate.now().minusDays(30), LocalDate.now().minusDays(29), "Esame Matematica 1", t1, c1),
                new Exercise(LocalDate.now().minusDays(28), LocalDate.now().minusDays(27), "Esame Matematica 2", t1, c1),
                new Exercise(LocalDate.now().minusDays(26), LocalDate.now().minusDays(25), "Esame Fisica 1", t2, c2),
                new Exercise(LocalDate.now().minusDays(24), LocalDate.now().minusDays(23), "Esame Fisica 2", t2, c2),
                new Exercise(LocalDate.now().minusDays(22), LocalDate.now().minusDays(21), "Esame Informatica 1", t3, c3),
                new Exercise(LocalDate.now().minusDays(20), LocalDate.now().minusDays(19), "Esame Informatica 2", t3, c3),
                new Exercise(LocalDate.now().minusDays(18), LocalDate.now().minusDays(17), "Esame Chimica 1", t4, c4),
                new Exercise(LocalDate.now().minusDays(16), LocalDate.now().minusDays(15), "Esame Chimica 2", t4, c4),
                new Exercise(LocalDate.now().minusDays(14), LocalDate.now().minusDays(13), "Esame Speciale", t1, c1),
                new Exercise(LocalDate.now().minusDays(12), LocalDate.now().minusDays(11), "Esame di Recupero", t2, c2)
        );
        exerciseRepository.saveAll(allExercises);

        // ==== CREAZIONE PAPER E VALUTATION ====
        List<Paper> allPapers = new ArrayList<>();
        List<Valuation> allValuations = new ArrayList<>();
        for (int i = 0; i < allStudents.size(); i++) {
            Student student = allStudents.get(i);
            Exercise exam = allExercises.get(i % allExercises.size());
            Paper p = new Paper("Commento dello studente " + student.getName(),
                    LocalDate.now().minusDays(5),
                    "http://files.com/paper" + (i + 1) + ".pdf",
                    exam,
                    student);
            allPapers.add(p);
        }
        paperRepository.saveAll(allPapers);

        for (Paper p : allPapers) {
            Valuation v = new Valuation((int)(Math.random() * 11), "Buon lavoro", p);
            allValuations.add(v);
        }
        valutationRepository.saveAll(allValuations);

        // ==== CREAZIONE RESOURCE E STUDENTRESOURCE ====
        List<Resource> resources = List.of(
                new Resource("Testo 1", "Algebra", LocalDate.now().minusDays(20), t1),
                new Resource("Testo 2", "Fisica 2", LocalDate.now().minusDays(15), t2),
                new Resource("Testo 3", "Programmazione", LocalDate.now().minusDays(10), t3),
                new Resource("Testo 4", "Chimica Organica", LocalDate.now().minusDays(8), t4),
                new Resource("Testo 5", "Analisi", LocalDate.now().minusDays(5), t1)
        );
        resourceRepository.saveAll(resources);

        List<StudentResource> studentResources = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = allStudents.get(i);
            Resource res = resources.get(i % resources.size());
            studentResources.add(new StudentResource(LocalDate.now().minusDays(1), student, res));
        }
        studentResourceRepository.saveAll(studentResources);

        // ==== CREAZIONE ATTACHMENT E NOTE ====
        Attachment att = new Attachment("file.pdf", "http://file.com/att1", "PDF", c1);
        attachmentRepository.save(att);
        Note note = new Note("Nota importante", "Descrizione nota", LocalDate.now(), att, allStudents.get(0));
        noteRepository.save(note);
    }
}
