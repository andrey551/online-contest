import React, {useEffect, useState} from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import './styles.css';
import {useDispatch, useSelector} from "react-redux";
import {getDetailEvent, getSubmissionsEvent} from "../../../../hooks/modules/submission";


const SubmissionHistory = () => {
    // const [selectedSubmission, setSelectedSubmission] = useState(null);
    // const [submissions, setSubmissions] = useState(generateTestData());

    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(getSubmissionsEvent())
    }, []);
    const selectedSubmission = useSelector((state => state.submission.submissionDetail));
    const submissions = useSelector((state => state.submission.submissions));

    const handleSelectSubmission = (id) => {
        dispatch(getDetailEvent(id));
    }

    const processChartData = (submission) => {
        if (!submission) return [];

        return submission.results.status_list.map((status, index) => ({
            name: `Test ${index + 1}`,
            time: submission.results.time_response[index],
            memory: submission.results.memory_used[index],
            status: status
        }));
    };

    const getStatusColor = (status) => {
        switch (status) {
            case 'passed': return '#4CAF50';
            case 'failed': return '#F44336';
            case 'time_limit': return '#FF9800';
            case 'memory_limit': return '#9C27B0';
            default: return '#607D8B';
        }
    };

    const getStatusText = (status) => {
        switch (status) {
            case 'passed': return 'Passed';
            case 'failed': return 'Failed';
            case 'time_limit': return 'Time Limit';
            case 'memory_limit': return 'Memory Limit';
            default: return status;
        }
    };

    return (
        <div className="submission-container">
            {/* Top 75% - Submission Details */}
            <div className="submission-details">
                {selectedSubmission ? (
                    <div className="details-content">
                        <h3 className="details-header">Submission Details
                            - {new Date(selectedSubmission.submissionDate).toLocaleString()}</h3>

                        <div className="stats-group">
                            <div className="stat-box">
                                <div className="stat-content">
                                    <span className="stat-label">Total Test Cases</span>
                                    <span className="stat-value">{selectedSubmission.results.total}</span>
                                </div>
                            </div>
                            <div className="stat-box">
                                <div className="stat-content">
                                    <span className="stat-label">Passed</span>
                                    <span className="stat-value">{selectedSubmission.results.passed}</span>
                                </div>
                            </div>
                            <div className="stat-box">
                                <div className="stat-content">
                                    <span className="stat-label">Status</span>
                                    <span
                                        className="stat-value"
                                        style={{
                                            color: getStatusColor(
                                                selectedSubmission.results.status_list.every(s => s === 'passed') ?
                                                    'passed' : 'failed'
                                            )
                                        }}
                                    >
                                    {selectedSubmission.results.status_list.every(s => s === 'passed') ?
                                        'All Passed' : 'Some Failed'}
                                </span>
                                </div>
                            </div>
                        </div>

                        <div className="chart-container">
                            <h4 className="chart-title">Performance Metrics</h4>
                            <ResponsiveContainer width="100%" height={300}>
                                <LineChart data={processChartData(selectedSubmission)}>
                                    <CartesianGrid strokeDasharray="3 3" stroke="#eee"/>
                                    <XAxis dataKey="name" tick={{fontSize: 12}}/>
                                    <YAxis yAxisId="left" label={{value: 'Time (ms)', angle: -90, fontSize: 12}}/>
                                    <YAxis yAxisId="right" orientation="right"
                                           label={{value: 'Memory (MB)', angle: 90, fontSize: 12}}/>
                                    <Tooltip/>
                                    <Legend/>
                                    <Line
                                        yAxisId="left"
                                        type="monotone"
                                        dataKey="time"
                                        name="Time (ms)"
                                        stroke="#8884d8"
                                        strokeWidth={2}
                                    />
                                    <Line
                                        yAxisId="right"
                                        type="monotone"
                                        dataKey="memory"
                                        name="Memory (MB)"
                                        stroke="#82ca9d"
                                        strokeWidth={2}
                                    />
                                </LineChart>
                            </ResponsiveContainer>
                        </div>

                        <div className="test-results">
                            <h4 className="results-title">Test Case Results</h4>
                            <div className="results-table-container">
                                <table className="results-table">
                                    <thead>
                                    <tr>
                                        <th>Test Case</th>
                                        <th>Status</th>
                                        <th>Time (ms)</th>
                                        <th>Memory (MB)</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {selectedSubmission.results.status_list.map((status, index) => (
                                        <tr key={index}>
                                            <td>Test {index + 1}</td>
                                            <td>
                                            <span className="status-badge" style={{
                                                backgroundColor: getStatusColor(status)
                                            }}>
                                                {getStatusText(status)}
                                            </span>
                                            </td>
                                            <td>{selectedSubmission.results.time_response[index]?.toFixed(2) || 'N/A'}</td>
                                            <td>{selectedSubmission.results.memory_used[index]?.toFixed(2) || 'N/A'}</td>
                                        </tr>
                                    ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div className="download-section">
                            <button className="download-btn" disabled={!selectedSubmission.downloadLink}>
                                Download
                            </button>
                        </div>
                    </div>
                ) : (
                    <div className="empty-state">
                        <div className="empty-icon">📄</div>
                        <p className="empty-text">Select a submission from the list below to view details</p>
                    </div>
                )}
            </div>

            {/* Bottom 25% - Submission List */}
            <div className="submission-list">
                <h3 className="list-header">Submission History</h3>
                {submissions?.submissionRawList?.length > 0 ? (
                    <div className="list-table-container">
                        <table className="list-table">
                            <thead>
                            <tr>
                                <th>Date</th>
                                <th>Status</th>
                                <th>Details</th>
                            </tr>
                            </thead>
                            <tbody>
                            {submissions.submissionRawList.map((submission) => (
                                <tr
                                    key={submission.submissionId}
                                    onClick={() => handleSelectSubmission(submission.submissionId)}
                                    className={selectedSubmission?.submissionId === submission.submissionId ? 'selected' : ''}
                                >
                                    <td>{new Date(submission.submitTime).toLocaleString()}</td>
                                    <td>
                                <span style={{
                                    color: getStatusColor(submission.state)
                                }}>
                                    {getStatusText(submission.state)}
                                </span>
                                    </td>
                                    <td>
                                        <button className="view-btn">
                                            View
                                        </button>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                ) : (
                    <p className="no-submissions">No submissions yet</p>
                )}
            </div>
        </div>
    );
};

export default SubmissionHistory;