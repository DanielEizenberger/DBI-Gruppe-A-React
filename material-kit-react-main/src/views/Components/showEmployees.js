import {makeStyles} from "@material-ui/core/styles";
import styles from "../../assets/jss/material-kit-react/views/components";
import Parallax from "../../components/Parallax/Parallax";
import GridContainer from "../../components/Grid/GridContainer";
import GridItem from "../../components/Grid/GridItem";
import Button from "../../components/CustomButtons/Button";
import {Link} from "react-router-dom";
import classNames from "classnames";
import React from "react";
import Table from "react-bootstrap/Table";
import axios from "axios";
import ownStyles from '../../assets/css/myOwnStyle.module.css'

const useStyles = makeStyles(styles);

export default function showEmployees() {
    const classes = useStyles();
    const [rows, setRows] = React.useState([]);



    const deleteRow = (id) => {
        axios.delete(`http://localhost:8081/employeeBackend/id=${id}`)
            .then((response) => {
                const newRows = rows.filter(x => x.id != response.data.id)
                setRows(newRows)
            })

    }


    const fetchAllEmployees = () => {
        axios.get('http://localhost:8081/employeeBackend')
            .then((response) => {

                setRows(response.data)

            })
    }



    React.useEffect(() => {
        fetchAllEmployees()
    }, []);

    return (
        <div>

            <Parallax image={require("assets/img/boat-5889919.png").default}>
                <div className={classes.container}>
                    <GridContainer>
                        <GridItem>
                            <div className={classes.brand}>
                                <h1 className={classes.title}>Service Application</h1>
                                <h3 className={classes.subtitle}>HTL Grieskirchen</h3>
                                <a href="/">
                                    <Button className={classes.button} color="primary">
                                        <Link to="/">Zurück</Link>
                                    </Button>
                                </a>
                                <a href="/newEmployee">
                                <Button className={classes.button} color="success">
                                    <Link to="/newEmployee">Neuen Mitarbeiter anlegen</Link>
                                </Button>
                                </a>
                            </div>
                        </GridItem>
                    </GridContainer>
                </div>
            </Parallax>

            <div className={classNames(classes.main, classes.mainRaised, ownStyles.mainRaised)}>

                <Table responsive striped>
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Vorname</th>
                        <th scope="col">Nachname</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>

                    <tbody>
                    {rows.map(item =>
                        <tr key={item.id}>
                            <th scope="row">{item.id}</th>
                            <td>{item.firstname}</td>
                            <td>{item.lastname}</td>

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
        </div>
    );
}