import React from 'react'
import { Routes, Route } from 'react-router-dom'
import Navbar from './components/Navbar/Navbar'
import Sidebar from './components/Sidebar/Sidebar'
import Home from './pages/home/Home'
import Video from './pages/video/Video'

const App = () => {
  return (
    <div>
      <Navbar />
      <div style={{ display: 'flex' }}>
        <Sidebar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/video/:categoryId/:videoId" element={<Video />} />
        </Routes>
      </div>
    </div>
  )
}

export default App

/*
import React from 'react'
import { Routes, Route } from 'react-router-dom'
import Navbar from './components/Navbar/Navbar'
import Sidebar from './components/Sidebar/Sidebar'
import Home from './pages/Home'
import Video from './pages/Video'

const App = () => {
  return (
    <div>
      <Navbar />
      <div style={{ display: 'flex' }}>
        <Sidebar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/video/:categoryId/:videoId" element={<Video />} />
        </Routes>
      </div>
    </div>
  )
}

export default App

*/

