import React from 'react';
import './styles.css';
import {useDispatch} from "react-redux";
import {useNavigate} from "react-router-dom"
import {setCurrentCourse} from "../../hooks/modules/state";

const Course = ({course }) => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const submitOnAction = () => {
        dispatch(setCurrentCourse(course));
        navigate('/laboratory')
    }
    console.log(course)
    return (
        <div className="card course-container">
            <div className="course-card-header">
                <h4>Course</h4>
            </div>
            <div className="card-body">
                <div className="course-title">
                    <h1 className="course-card-title-content">{course.courseName}</h1>
                    <div className="course-card-title-period">{course.semester}</div>
                </div>
                <div className="course-description">{course.description}</div>
            </div>
            <div className="card-footer">
                <a href="/" className="detail-btn" onClick={(e) => {
                    e.preventDefault();
                    submitOnAction();
                }}>Detail</a>
            </div>
        </div>
    );
};

export default Course;