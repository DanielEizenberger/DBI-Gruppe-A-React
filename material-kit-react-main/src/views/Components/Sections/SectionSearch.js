import GridItem from "../../../components/Grid/GridItem";
import GridContainer from "../../../components/Grid/GridContainer";
import CustomInput from "../../../components/CustomInput/CustomInput";
import React from "react";
import CustomDropdown from "../../../components/CustomDropdown/CustomDropdown";
import styles from '../../../assets/css/myOwnStyle.module.css'

export default function SectionSearch() {
    React.useEffect(() => {});
    return (
        <div>
            <GridContainer>

                <GridItem md={5} className={styles.searchBar}>
                    <CustomInput
                        labelText="Search"
                        id="float"
                        formControlProps={{
                            fullWidth: true
                        }}
                    />
                </GridItem>
                <GridItem md={5}>
                    <div className={styles.standard}>
                        <CustomDropdown
                            hoverColor="primary"
                            dropdownHeader="Filter by"
                            buttonText="Filter by"
                            buttonProps={{
                                round: true,
                                color: "info"
                            }}
                            dropdownList={[
                                "ID",
                                "Dienstname",
                                "Mitarbeiter",
                                "Datum",
                            ]}
                        />
                    </div>

                </GridItem>
            </GridContainer>
        </div>
    )
}