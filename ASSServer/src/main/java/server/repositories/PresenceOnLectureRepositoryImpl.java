package server.repositories;

import com.querydsl.jpa.impl.JPAQueryFactory;
import entities.PresenceOnLecture;
import entities.QPresenceOnLecture;
import entities.Student;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;

public class PresenceOnLectureRepositoryImpl implements PresenceOnLectureRepositoryCustom {

    @Inject
    private EntityManager em;

    public PresenceOnLecture findByPresenceDateAndHourTimeRoomAndStudent(Date date, String hour, String room, Student student) {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QPresenceOnLecture presenceOnLecture = QPresenceOnLecture.presenceOnLecture;

        return query.select(presenceOnLecture)
                .from(presenceOnLecture)
                .where(presenceOnLecture.presenceDate.eq(date)
                        .and(presenceOnLecture.hourTime.eq(hour))
                .and(presenceOnLecture.student.id.eq(student.getId())))
                .fetchOne();

    }

}
