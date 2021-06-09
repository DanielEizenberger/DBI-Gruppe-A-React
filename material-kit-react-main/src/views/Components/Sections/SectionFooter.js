import React from "react";

import Button from "../../../components/CustomButtons/Button";
import styles from '../../../assets/css/myOwnStyle.module.css'



export default function SectionFooter() {
    React.useEffect(() => {});
    return (
        <div>
            <Button color="success">Zurück</Button>
            <Button color="success" className={styles.right}>Weiter</Button>
        </div>
    );
}