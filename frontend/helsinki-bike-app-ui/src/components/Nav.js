import React from "react";
import { Link } from "react-router-dom";

export default function Nav(){

  return(
        <div className="navbar">
           <ul className="nav-links">
              <Link to="/">Home</Link>
              <Link to="/journeys">Journeys</Link>
              <Link to="/stations">Stations</Link>
           </ul>
        </div>
  );

}