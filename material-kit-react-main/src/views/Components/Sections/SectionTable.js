import React from "react";

import Table from 'react-bootstrap/Table';
import "assets/scss/material-kit-react.scss?v=1.10.0";

export default function SectionTable() {
  React.useEffect(() => {});
  return (
    <div>

          <Table responsive>
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Dienstname</th>
                <th scope="col">Mitarbeiter</th>
                <th scope="col">Datum</th>

              </tr>
            </thead>

            <tbody>
              <tr>
                <th scope="row">1</th>
                <td>Stall ausräumen</td>
                <td>Otto</td>
                <td>06.06.2021</td>
              </tr>
              <tr>
                <th scope="row">2</th>
                <td>Rasenmähen</td>
                <td>Julia</td>
                <td>20.03.2020</td>
              </tr>
              <tr>
                <th scope="row">3</th>
                <td>Putzen</td>
                <td>Hans</td>
                <td>23.01.2019</td>
              </tr>
            </tbody>
          </Table>

    </div>
  );
}
