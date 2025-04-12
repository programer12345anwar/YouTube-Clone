import React, { useEffect, useState } from 'react';
import './Recommended.css';
import { API_KEY, value_converter } from '../../Data';
import { Link } from 'react-router-dom';

const Recommended = ({ categoryId }) => {
  const [apiData, setApiData] = useState([]);

  const fetchData = async () => {
    const relatedVideo_url = `https://youtube.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cstatistics&chart=mostPopular&maxResults=50&regionCode=IN&videoCategoryId=${categoryId}&key=${API_KEY}`;
    try {
      const response = await fetch(relatedVideo_url);
      const result = await response.json();
      setApiData(result.items || []);
    } catch (error) {
      console.log("Error fetching recommended videos:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, [categoryId]);

  return (
    <div className='recommended'>
      {apiData.map((item, index) => (
        <Link
          to={`/video/${item?.snippet?.categoryId}/${item?.id}`}
          className="side-video-list"
          key={item.id}
        >
          <img src={item?.snippet?.thumbnails?.medium?.url} alt="thumbnail" />
          <div className="vid-info">
            <h4>{item?.snippet?.title}</h4>
            <p>{item?.snippet?.channelTitle}</p>
            <p>{value_converter(item?.statistics?.viewCount)} views</p>
          </div>
        </Link>
      ))}
    </div>
  );
};

export default Recommended;
