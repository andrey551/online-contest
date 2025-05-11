import React, {useEffect, useState} from 'react';
import './styles.css';
import {useDispatch, useSelector} from "react-redux";

import {getLaboratoriesEvent} from "../../../hooks/modules/laboratory";
import {useNavigate} from "react-router-dom";
import {setCurrentLaboratory} from "../../../hooks/modules/state";

const LaboratoriesPage = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate()
    useEffect(() => {
        dispatch(getLaboratoriesEvent())
    }, [])

    const submitOnAction = (lab) => {
        dispatch(setCurrentLaboratory(lab));
        navigate('/submit-board')
    }
    const course = useSelector((state => state.crtState.crtCourse));
    const laboratories = useSelector((state => state.laboratory.laboratories.laboratories))|| [];

    const [expandedLab, setExpandedLab] = useState(null);

    const toggleExpand = (labId) => {
        setExpandedLab(expandedLab === labId ? null : labId);
    };

    const formatDate = (dateString) => {
        const options = { month: 'short', day: 'numeric' };
        return new Date(dateString).toLocaleDateString(undefined, options);
    };

    const splitTags = (tagString) => {
        return tagString.split(',').filter(tag => tag.trim() !== '');
    };

    return (
        <div className="laboratories-page">
            {/* Course Header */}
            <header className="course-header">
                <h1 className="course-title">{course.courseName}</h1>
                <div className="course-meta">
                    <span className="course-teacher">{course.teacherName}</span>
                    <span className="course-semester">{course.semester}</span>
                </div>
            </header>

            <div className="labs-container">
                <h2 className="labs-title">Laboratory Assignments</h2>
                <div className="labs-list">
                    {laboratories.map((lab) => (
                        <div
                            key={lab.id}
                            className={`lab-item ${expandedLab === lab.id ? 'expanded' : ''}`}
                        >
                            <div className="lab-content">
                                <div className="lab-main-info">
                                    <h3 className="lab-name">{lab.title}</h3>
                                    <div className="lab-tags">
                                        {splitTags(lab.tags).map((tag, index) => (
                                            <span key={index} className="lab-tag">{tag}</span>
                                        ))}
                                    </div>
                                </div>
                                <div className="lab-secondary-info">
                                    <span className="lab-deadline">Due {formatDate(lab.deadline)}</span>
                                    <button
                                        className="detail-button"
                                        onClick={() => toggleExpand(lab.id)}
                                        aria-expanded={expandedLab === lab.id}
                                    >
                                        <span className={`arrow-icon ${expandedLab === lab.id ? 'expanded' : ''}`}></span>
                                    </button>
                                </div>
                            </div>

                            {expandedLab === lab.id && (
                                <div className="lab-details">
                                    {/*<p className="lab-description">{lab.description}</p>*/}
                                    <div className="lab-actions">
                                        <button className="action-button materials" >Materials</button>
                                        <button className="action-button submit" onClick={(e) => {
                                            e.preventDefault();
                                            submitOnAction(lab);
                                        }}>Submit</button>
                                    </div>
                                </div>
                            )}
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default LaboratoriesPage;