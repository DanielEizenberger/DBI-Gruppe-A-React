import Parallax from "../../components/Parallax/Parallax";
import GridContainer from "../../components/Grid/GridContainer";
import GridItem from "../../components/Grid/GridItem";
import {Link} from "react-router-dom";
import classNames from "classnames";
import React from "react";
import Button from "../../components/CustomButtons/Button";
import {makeStyles} from "@material-ui/core/styles";
import Datetime from "react-datetime";
import styles from "assets/jss/material-kit-react/views/components.js";
import CustomInput from "../../components/CustomInput/CustomInput";
import FormControl from "@material-ui/core/FormControl";
import CustomDropdown from "../../components/CustomDropdown/CustomDropdown";
import axios from "axios";
import ownStyles from '../../assets/css/myOwnStyle.module.css'

const useStyles = makeStyles(styles);

export default function newService() {
    const classes = useStyles();

    const [name, setName] = React.useState("");
    const [mitarbeiter, setMitarbeiter] = React.useState("");
    const [datum, setDatum] = React.useState("");
    const [adresse, setAdresse] = React.useState("");

    const [fillMitarbeiter, setFillMitarbeiter] = React.useState([]);

    const addService = () => {
        axios.post('http://localhost:8080/serviceBackend', {
            name: name,
            employeeId: fillMitarbeiter.filter(x => x.name === mitarbeiter).map(x => x.id)[0],
            date: datum,
            address: adresse
        })
            .then((response) => {
                console.log('Service added', response.data);


            })
    }



    const fillDropdown = () => {
        axios.get('http://localhost:8081/employeeBackend')
            .then((response) => {
                console.log('Mitarbeiter gegetted', response.data);
                const mitarbeiterArray = [];
                response.data.forEach(x => mitarbeiterArray.push({name: x.firstname + " " + x.lastname, id: x.id}))
                setFillMitarbeiter(mitarbeiterArray)
            })
    }
    React.useEffect(() => {
        fillDropdown()
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
                             labelText="Name:"
                             id="float"
                             formControlProps={{
                                 fullWidth: true

                             }}
                             inputProps={{
                                 onChange: (e) => setName(e.target.value)

                             }}
                />


                <div>
                    <CustomDropdown
                                    buttonText={mitarbeiter !== "" ? mitarbeiter : "Mitarbeiter:"}
                                    dropdownList={

                                        fillMitarbeiter.map(x => x.name)
                                    }
                                    onClick={(selectedItem)=>{setMitarbeiter(selectedItem) }}

                    />
                </div>


                <CustomInput
                             labelText="Adresse:"
                             id="float"
                             formControlProps={{
                                 fullWidth: true
                             }}
                             inputProps={{
                                 onChange: (e) => setAdresse(e.target.value)

                             }}
                />

                <div>
                    <FormControl fullWidth>
                        <Datetime onChange={(e) => setDatum(e.format("DD-MM-yyyy"))}
                                  inputProps={{placeholder: "Datum:"}}
                        />
                    </FormControl>
                </div>


                <Button onClick={() => {
                    addService();
                }}
                        color="primary">
                    Anlegen
                </Button>

            </div>
        </div>)
}