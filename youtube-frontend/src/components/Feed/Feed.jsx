import React, { useState, useEffect } from 'react'
import thumbnail1 from '../../assets/thumbnail1.png'
import './Feed.css'
import { Link } from 'react-router-dom'
import { API_KEY } from '../../data.js'

const Feed = ({ category }) => {
  const [data, setData] = useState([])

  useEffect(() => {
    const fetchData = async () => {
      const videoList_url = `https://youtube.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cstatistics&chart=mostPopular&maxResults=50&regionCode=IN&videoCategoryId=${category}&key=${API_KEY}`

      const response = await fetch(videoList_url)
      const result = await response.json()
      setData(result.items || [])
    }

    fetchData()
  }, [category])

  return (
    <div className='feed'>
      {data.map((item, index) => (
        <Link
          to={`video/${item.snippet.categoryId}/${item.id}`}
          className='card'
          key={index}
        >
          <img src={item.snippet.thumbnails.medium.url} alt='' />
          <h2>{item.snippet.title}</h2>
          <h3>{item.snippet.channelTitle}</h3>
          <p>{item.statistics.viewCount} views &bull; {item.snippet.publishedAt}</p>
        </Link>
      ))}

      <Link to={`video/20/5421`} className='card'>
        <img src={thumbnail1} alt='' />
        <h2>Best channel to learn coding that helps you become a good software engineer</h2>
        <h3>GreatStack</h3>
        <p>15K views &bull; 2 days ago</p>
      </Link>
    </div>
  )
}

export default Feed
