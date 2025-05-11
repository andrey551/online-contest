import React from 'react';
import { FiArrowRight } from 'react-icons/fi';
import './styles.css';

const LaboratoryList = ({ laboratories, onLabClick }) => {
    return (
        <div className="lab-list-container">
            {laboratories.map((lab) => (
                <div key={lab.id} className="lab-card" onClick={() => onLabClick(lab.id)}>
                    <div className="lab-content">
                        <div className="lab-text">
                            <h3>{lab.title}</h3>
                            <p className="description">{lab.description}</p>

                            <div className="lab-meta">
                                <div className="tags">
                                    {lab.tags.split(',').map((tag, index) => (
                                        <span key={index} className="tag">{tag.trim()}</span>
                                    ))}
                                </div>
                                <span className="deadline">
                  ⏱️ {new Date(lab.deadline).toLocaleDateString()}
                </span>
                            </div>
                        </div>

                        <button
                            className="view-detail-btn"
                            onClick={(e) => {
                                e.stopPropagation();
                                onLabClick(lab.id);
                            }}
                            aria-label="View details"
                        >
                            <span>Details</span>
                            <FiArrowRight className="arrow-icon" />
                        </button>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default LaboratoryList;