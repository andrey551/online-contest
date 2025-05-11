import React, {useEffect} from "react";

import Header from "../../../components/header/header";

import "./styles.css"
import Course from "../../../components/course-card";
import {useDispatch, useSelector} from "react-redux";
import {getCoursesEvent} from "../../../hooks/modules/course";
import {checkIsTeacher} from "../../../hooks/modules/user";

const Courses = () => {
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(getCoursesEvent())
    }, [])
    useEffect(() => {
        dispatch(checkIsTeacher())
    }, [])

    const courses = useSelector((state => state.course.courses.courses) || [])
    const isTeacher = useSelector((state => state.user.isTeacher))

    return (
        <>
            <div className={"teacher-homepage-container"}>
                <div className={"header-container"} >
                    <Header />
                </div>
                <div className={"body-container"}>
                    <div className={"left-center-container"}>
                        {isTeacher && (
                            <div className="add-course-card">
                                <button
                                    className="add-course-button"
                                    onClick={() => {/* Add course handler */}}
                                    title="Add new course"
                                >
                                    <span className="plus-icon">+</span>
                                    <span className="button-text">Add Course</span>
                                </button>
                            </div>
                        )}
                        {Array.isArray(courses) && courses.length > 0 ? (
                            courses.map((course, index) => (
                                <Course
                                    key={index}
                                    course={course}
                                />
                            ))
                        ) : (
                            <p style={{ padding: "1rem", color: "#888" }}>No courses available.</p>
                        )}
                    </div>
                </div>
            </div>
        </>
    )
}

export default Courses;