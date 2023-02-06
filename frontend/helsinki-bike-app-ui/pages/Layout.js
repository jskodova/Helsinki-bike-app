import { Outlet, Link } from "react-router-dom";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

const Layout = () => {
  return (
    <>
      <Navbar bg="dark" variant="dark">
        <Container>
          <Navbar.Brand href="#home">Solita</Navbar.Brand>
          <Nav className="list me-auto">
            <Nav.Link><Link to="/journeys">Journeys</Link></Nav.Link>
            <Nav.Link><Link to="/journeys">Add a journey</Link></Nav.Link>
            <Nav.Link><Link to="/stations">Stations</Link></Nav.Link>
            <Nav.Link><Link to="/journeys">Add a station</Link></Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      <br />
      <Outlet />
    </>
  )
};

export default Layout;