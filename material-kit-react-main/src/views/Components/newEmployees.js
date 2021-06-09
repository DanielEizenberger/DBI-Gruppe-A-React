
import styles from "assets/jss/material-kit-react/views/components.js";
import CustomInput from "../../components/CustomInput/CustomInput";
import axios from "axios";
import classNames from "classnames";
import makeStyles from "@material-ui/core/styles/makeStyles";
import React from "react";
import Parallax from "../../components/Parallax/Parallax";
import GridContainer from "../../components/Grid/GridContainer";
import GridItem from "../../components/Grid/GridItem";
import Button from "../../components/CustomButtons/Button";
import {Link} from "@material-ui/core";
const useStyles = makeStyles(styles);
import ownStyles from '../../assets/css/myOwnStyle.module.css'

export default function newEmployees() {
    const classes = useStyles();

    const [firstname, setFirstname] = React.useState("");
    const [lastname, setLastname] = React.useState("");



    const addEmployee = () => {
        axios.post('http://localhost:8081/employeeBackend', {
            firstname: firstname,
            lastname: lastname,

        })
            .then((response) => {
                console.log('Service added', response.data);


            })
    }




    React.useEffect(() => {

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
                                <Button className={classes.button} color="primary">
                                    <Link to="/">Zurück</Link>
                                </Button>

                            </div>
                        </GridItem>
                    </GridContainer>
                </div>
            </Parallax>

            <div className={classNames(classes.main, classes.mainRaised, ownStyles.mainRaised)}>


                <CustomInput
                    labelText="Vorname:"
                    id="float"
                    formControlProps={{
                        fullWidth: true

                    }}
                    inputProps={{
                        onChange: (e) => setFirstname(e.target.value)

                    }}
                />

                <CustomInput
                    labelText="Nachname:"
                    id="float"
                    formControlProps={{
                        fullWidth: true

                    }}
                    inputProps={{
                        onChange: (e) => setLastname(e.target.value)

                    }}
                />





                <Button onClick={() => {
                    addEmployee();
                }}
                        color="primary">
                    Anlegen
                </Button>

            </div>
        </div>)
}