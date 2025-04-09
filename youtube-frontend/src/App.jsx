import React, { useState } from 'react'
import { Routes, Route } from 'react-router-dom'
import Navbar from './components/Navbar/Navbar'
import Sidebar from './components/Sidebar/Sidebar'
import Home from './pages/home/Home'
import Video from './pages/video/Video'

const App = () => {
  const [sidebar,setSidebar]=useState(true);
  return (
    <div>
       <Navbar setSidebar={setSidebar}/>
        <Routes>
          <Route path="/" element={<Home sidebar={sidebar}/>} />
          <Route path="/video/:categoryId/:videoId" element={<Video />} />
        </Routes>
    </div>
  )
}

export default App



 