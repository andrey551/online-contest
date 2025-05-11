import React, { useState } from "react";
import "./styles.css";
import TestSetCreator from "./step-three";
import DescriptionEditor from "./step-two";
import InfoForm from "./step-one";
import ResourceGenerator from "./step-four";

const CreateCourse = () => {
    const steps = ["Info", "Description", "Tests", "Review"];
    const [currentStep, setCurrentStep] = useState(0);

    const goToStep = (index) => setCurrentStep(index);
    const nextStep = () => {
        if (currentStep < steps.length - 1) {
            setCurrentStep(currentStep + 1);
        } else {
            alert("Done!");
        }
    };

    const percent = (currentStep / (steps.length - 1)) * 100;

    return (
        <div className="create-course-container">
            <div className="RSPBprogressBar">
                <div className="RSPBprogression" style={{ width: `${percent}%` }} />
                {steps.map((_, index) => (
                    <div
                        key={index}
                        className={`RSPBstep`}
                        style={{ left: `${(index / (steps.length - 1)) * 100}%` }}
                    >
                        <div
                            className={`indexedStep ${index <= currentStep ? "accomplished" : ""}`}
                            onClick={() => goToStep(index)}
                        >
                            {index + 1}
                        </div>
                    </div>
                ))}
            </div>

            <div className="form-container">
                {currentStep === 0 && <InfoForm toNextPage={nextStep} />}
                {currentStep === 1 && <DescriptionEditor toNextPage={nextStep} />}
                {currentStep === 2 && <TestSetCreator toNextPage={nextStep} />}
                {currentStep === 3 && <ResourceGenerator />}
            </div>
        </div>
    );
};

export default CreateCourse;


