import AllJourneys from './pages/AllJourneys';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import React from 'react';
import './index.css';
import Layout from "./pages/Layout";

export default function App() {
    return (
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index/>
          <Route path="journeys" element={<AllJourneys />} />
          <Route path="contact"/>
          <Route path="*"/>
        </Route>
      </Routes>
    </BrowserRouter>
    );
  }