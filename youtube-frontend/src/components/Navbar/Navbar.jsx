import React from 'react'
// import '../index.css'
import './Navbar.css'
import menu_icon from '../../assets/menu.png'
import logo from '../../assets/logo.png'
import search_icon from '../../assets/search.png'
import more_icon from '../../assets/more.png'
import upload_icon from '../../assets/upload.png'
import notification_icon from '../../assets/notification.png'
import profile_icon from '../../assets/profile.jpg'
const Navbar = ({setSidebar}) => {
  return (
    <nav className='flex-div'>
       <div className='nav-left flex-div'>
        <img className='menu-icon' onClick={()=>setSidebar(prev=>prev===false?true:false)} src={menu_icon} alt="menu" />
        <img className='logo' src={logo} alt="logo" />
        </div>
        <div className='nav-middle flex-div'>
            <div className='search-box flex-div'>
            <input type="text" placeholder='Search' />
            <img src={search_icon} alt="search" />
            </div>
        </div>
        <div className='nav-right flex-div'>
           <img src={upload_icon} alt="upload icon" /> 
           <img src={more_icon} alt="" />
           <img src={notification_icon} alt="" />
           <img src={profile_icon} className='user-icon' alt="" />
        </div>
    </nav>
  )
}

export default Navbar
