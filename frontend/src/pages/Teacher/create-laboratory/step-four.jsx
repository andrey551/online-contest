import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector } from 'react-redux';
import './styles.css'
import {setResource} from "../../../hooks/modules/laboratory";
const ResourceGenerator= () => {
    const dispatch = useDispatch();
    const resources= useSelector((state => state.laboratory.laboratoryToAdd.resource))
    const [items, setItems] = useState(resources);
    const [imageName, setImageName] = useState("");
    const [command, setCommand] = useState("");
    const [user, setUser] = useState("");

    useEffect(() => {
        setItems(resources);
    }, [resources]);

    const handleAddResource = () => {
        const newResource = {
            image_name: imageName,
            command: command,
            user: user,
        };
        dispatch(setResource(newResource));

        // Reset the fields after adding
        setImageName("");
        setCommand("");
        setUser("");
    };

    const toggleDetails = (index) => {
        const updatedResources = [...items];
        updatedResources[index].isOpen = !updatedResources[index].isOpen;
        setItems(updatedResources);
    };

    return (
        <div className="resource-creator-container">
            <h2>Create a New Resource</h2>

            <div className="new-resource-form">
                <label>Image Name:</label>
                <input
                    type="text"
                    value={imageName}
                    onChange={(e) => setImageName(e.target.value)}
                    placeholder="e.g., python:3.9-slim"
                />

                <label>Command:</label>
                <input
                    type="text"
                    value={command}
                    onChange={(e) => setCommand(e.target.value)}
                    placeholder="e.g., pip install -r requirements.txt && uvicorn app.main:app"
                />

                <label>User:</label>
                <input
                    type="text"
                    value={user}
                    onChange={(e) => setUser(e.target.value)}
                    placeholder="e.g., root"
                />

                <button onClick={handleAddResource}>Add Resource</button>
            </div>

            <h3>Resources List</h3>
            <div className="resource-list">
                {resources.map((resource, index) => (
                    <div key={index} className="resource-item">
                        <div className="resource-header" onClick={() => toggleDetails(index)}>
                            <span>{resource.image_name}</span>
                            <span className={`toggle-arrow ${resource.isOpen ? "open" : ""}`}>▶</span>
                        </div>

                        <div className={`resource-details ${resource.isOpen ? "open" : ""}`}>
                            <p><strong>Command:</strong> {resource.command}</p>
                            <p><strong>User:</strong> {resource.user}</p>
                        </div>
                    </div>
                ))}

            </div>
            <div className="step-control">
                <input type={"submit"} value={"Submit"} />
            </div>
        </div>
    );
}

export default ResourceGenerator;