import React from "react";
import Button from "components/CustomButtons/Button.js";
import Table from 'react-bootstrap/Table';
import "assets/scss/material-kit-react.scss?v=1.10.0";

export default function SectionBasics() {
  React.useEffect(() => {});
  return (
    <div>
      <div>

        <div>
          <h2>Basic Elements</h2>
          <Button type="button" color="primary">
            Click if u hate Prettier/Prettier
          </Button>

        </div>
      </div>

      <div>
          <Table responsive>
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">First</th>
                <th scope="col">Last</th>
                <th scope="col">Handle</th>
                 <th scope="col">Handle</th>
              </tr>
            </thead>

            <tbody>
              <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
              </tr>
              <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
              </tr>
              <tr>
                <th scope="row">3</th>
                <td colSpan="2">Larry the Bird</td>
                <td>@twitter</td>
              </tr>
            </tbody>
          </Table>
      </div>
    </div>
  );
}
