import React, { useState } from "react";
import './styles.css'
import {useDispatch, useSelector} from "react-redux";
import {setTestSet} from "../../../hooks/modules/laboratory";

const TestSetCreator = ({ toNextPage }) => {
    const dispatch = useDispatch();
    const testCases = useSelector((state => state.laboratory.laboratoryToAdd.testSet))
    const [expandedIndex, setExpandedIndex] = useState(null);
    const [newTestCaseExpanded, setNewTestCaseExpanded] = useState(false);
    const [newCase, setNewCase] = useState({
        request: {
            url: "",
            method: "POST",
            header: "",
            body: "",
        },
        response: {
            status_code: 200,
            header: "",
            body: "",
        },
    });

    const toggleExpand = (idx) => {
        setExpandedIndex(expandedIndex === idx ? null : idx);
    };

    const toggleNewTestCaseExpand = () => {
        setNewTestCaseExpanded(!newTestCaseExpanded);
    };

    const handleChange = (section, field, value) => {
        setNewCase({
            ...newCase,
            [section]: {
                ...newCase[section],
                [field]: value,
            },
        });
    };

    const handleAdd = () => {
        try {
            const formatted = {
                request: {
                    url: newCase.request.url,
                    method: newCase.request.method,
                    header: JSON.parse(newCase.request.header || "{}"),
                    body: newCase.request.body,
                },
                response: {
                    status_code: newCase.response.status_code,
                    header: JSON.parse(newCase.response.header || "{}"),
                    body: newCase.response.body,
                },
            };
            dispatch(setTestSet( formatted));
            setNewCase({
                request: { url: "", method: "POST", header: "", body: "" },
                response: { status_code: 200, header: "", body: "" },
            });
            setNewTestCaseExpanded(false);
        } catch (e) {
            alert("Header must be valid JSON.");
        }
    };

    return (
        <>
            <div className="test-set-creator-container">
                {/* Test Cases List */}
                <div className="test-set-container">
                    <h3>Current Test Cases</h3>
                    {testCases.length === 0 && <p>No test cases added yet.</p>}
                    {testCases.map((tc, idx) => (
                        <div key={idx} className="test-case-item">
                            <div className="test-case-header" onClick={() => toggleExpand(idx)}>
                                <span>Test Case {idx + 1}</span>
                                <button className="expand-btn">
                                    <span className={`arrow ${expandedIndex === idx ? "rotated" : ""}`}>▼</span>
                                </button>
                            </div>
                            {expandedIndex === idx && (
                                <pre className="test-case-detail">
                  {JSON.stringify(tc, null, 2)}
                </pre>
                            )}
                        </div>
                    ))}
                </div>

                {/* New Test Case Section */}
                <div className="new-test-container">
                    <h3 onClick={toggleNewTestCaseExpand} className="test-case-header">
                        New Test Case
                        <button className="expand-btn">
                            <span className={`arrow ${newTestCaseExpanded ? "rotated" : ""}`}>▼</span>
                        </button>
                    </h3>

                    {newTestCaseExpanded && (
                        <div className="test-case-form">
                            <fieldset>
                                <legend>Request</legend>
                                <input
                                    type="text"
                                    placeholder="URL"
                                    value={newCase.request.url}
                                    onChange={(e) => handleChange("request", "url", e.target.value)}
                                />
                                <select
                                    value={newCase.request.method}
                                    onChange={(e) => handleChange("request", "method", e.target.value)}
                                >
                                    {["GET", "POST", "PUT", "DELETE"].map((m) => (
                                        <option key={m}>{m}</option>
                                    ))}
                                </select>
                                <textarea
                                    placeholder="Header (JSON)"
                                    value={newCase.request.header}
                                    onChange={(e) => handleChange("request", "header", e.target.value)}
                                />
                                <textarea
                                    placeholder="Body"
                                    value={newCase.request.body}
                                    onChange={(e) => handleChange("request", "body", e.target.value)}
                                />
                            </fieldset>

                            <fieldset>
                                <legend>Response</legend>
                                <input
                                    type="number"
                                    placeholder="Status Code"
                                    value={newCase.response.status_code}
                                    onChange={(e) =>
                                        handleChange("response", "status_code", parseInt(e.target.value))
                                    }
                                />
                                <textarea
                                    placeholder="Header (JSON)"
                                    value={newCase.response.header}
                                    onChange={(e) => handleChange("response", "header", e.target.value)}
                                />
                                <textarea
                                    placeholder="Body"
                                    value={newCase.response.body}
                                    onChange={(e) => handleChange("response", "body", e.target.value)}
                                />
                            </fieldset>

                            <button className="add-btn" onClick={handleAdd}>Add Test Case</button>
                        </div>
                    )}
                </div>

                <div className="step-control">
                    <input type="submit" value="Next" onClick={() => toNextPage("pagefour")} />
                </div>
            </div>
        </>
    );
};

export default TestSetCreator;
