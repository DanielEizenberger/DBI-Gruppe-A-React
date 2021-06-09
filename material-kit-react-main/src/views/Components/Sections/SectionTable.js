import React from "react";

import Table from 'react-bootstrap/Table';
import "assets/scss/material-kit-react.scss?v=1.10.0";
import Button from "../../../components/CustomButtons/Button";
import axios from "axios";


export default function SectionTable() {

    const [rows, setRows] = React.useState([]);
    const [mitarbeiter, setMitarbeiter] = React.useState([]);

    const deleteRow = (id) => {
        axios.delete(`http://localhost:8080/serviceBackend/id=${id}`)
            .then((response )=>{
                const newRows = rows.filter(x => x.id != response.data.id)
                setRows(newRows)
            })
    }

    const fetchAllServices = () => {
        axios.get('http://localhost:8080/serviceBackend')
            .then((response) => {

                setRows(response.data)

            })
    }

    const fetchAllEmployees = () => {
        axios.get(`http://localhost:8081/employeeBackend/`)
            .then((response)=>{
                setMitarbeiter(response.data);
            })

    }

    React.useEffect(() => {
        fetchAllServices()
        fetchAllEmployees()
    }, []);
    return (
        <div>

            <Table responsive striped>
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Dienstname</th>
                    <th scope="col">Mitarbeiter</th>
                    <th scope="col">Datum</th>
                    <th scope="col">Edit</th>
                    <th scope="col">Delete</th>
                </tr>
                </thead>

                <tbody>
                {rows.map(item =>
                    <tr key={item.id}>
                        <th scope="row">{item.id}</th>
                        <td>{item.name}</td>
                        <td>{mitarbeiter.filter(x => x.id === item.employeeId)[0].firstname + " " + mitarbeiter.filter(x => x.id === item.employeeId)[0].lastname}</td>
                        <td>{item.date}</td>
                        <th>
                            <Button onClick={() => {
                                console.log('onClick');
                            }}>
                                Edit
                            </Button>
                        </th>
                        <th>
                            <Button onClick={() => {
                                deleteRow(item.id)
                            }}>
                                Delete
                            </Button>
                        </th>
                    </tr>
                )}
                </tbody>
            </Table>

        </div>
    );
}
