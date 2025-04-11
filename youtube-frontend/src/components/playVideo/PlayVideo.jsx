import React,{useState,useEffect} from "react";
import "./PlayVideo.css";
import like from "../../assets/like.png";
import dislike from "../../assets/dislike.png";
import share from "../../assets/share.png";
import save from "../../assets/save.png";

import user_profile from "../../assets/user_profile.jpg";
import { API_KEY, value_converter } from "../../Data";
import moment from "moment";
 
const PlayVideo = ({ videoId }) => {
    const [apiData, setApiData] = useState(null);
    const [channelData, setChannelData] = useState(null);  
    const [commentData, setCommentData] = useState(null);
    
     //Fetching video data from API
    const fetchVideoData=async () => {
        const videoDetails_url = `https://youtube.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cstatistics&id=${videoId}&key=${API_KEY}`
        
        await fetch(videoDetails_url)
        .then((response) => response.json())
        .then((result) => setApiData(result.items[0]))
        .catch((error) => console.log(error));  
    }
     //Fetching channel data from API
    const fetchOtherData=async () => {
        const channelDetails_url = `https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=${apiData.snippet.channelId}&key=${API_KEY}`
        
        await fetch(channelDetails_url)
        .then((response) => response.json())
        .then((result) => setChannelData(result.items[0]))
        .catch((error) => console.log(error));  
    }
    //Fetching Comments data from API
    const fetchCommentData = async () => {
        const comment_url = `https://youtube.googleapis.com/youtube/v3/commentThreads?part=snippet&videoId=${videoId}&key=${API_KEY}`;
        await fetch(comment_url)
          .then((response) => response.json())
          .then((result) => setCommentData(result.items))
          .catch((error) => console.log(error));
      };
       
    useEffect(() => {
        fetchVideoData();
        fetchCommentData(); // Call it here
      }, [videoId]);

    useEffect(() => {
        fetchOtherData();
    }, [apiData]);//when the apiData will be availabe then only the function will be called

    
      
    
  return (
    <div className="play-video">
      {/* <video src={video1} controls autoPlay muted></video> */}

      <iframe
        src={`https://www.youtube.com/embed/${videoId}?autoplay=1`} // note: removed space after embed/
        frameBorder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        referrerPolicy="strict-origin-when-cross-origin"
        allowFullScreen
      ></iframe>

      <h3>{apiData?apiData.snippet.title:"Title Here"}</h3>
      <div className="play-video-info">
        <p>{apiData?value_converter(apiData.statistics.viewCount):"16K"} views &bull;  {moment(apiData?.snippet.publishedAt).fromNow()}</p>
        <div>
          <span>
            <img src={like} alt="" />
            {apiData?value_converter(apiData.statistics.likeCount):"16K"}
          </span>
          <span>
            <img src={dislike} alt="" />
             
          </span>
          <span>
            <img src={share} alt="" />
            share
          </span>
          <span>
            <img src={save} alt="" />
            save
          </span>
        </div>
      </div>
      <hr />
      <div className="publisher">
        <img src={channelData?channelData.snippet.thumbnails.medium.url:""} alt="" />
        <div>
          <p>{apiData?apiData.snippet.channelTitle:"Channel Name"}</p>
          <span>{channelData?value_converter(channelData.statistics.subscriberCount):"16K"} subscribers</span>
        </div>
        <button>Subscribe</button>
      </div>
      <div className="vid-description">
        <p>{apiData?apiData.snippet.description.slice(0,250):"Description Here"}</p>
        <hr />
        <h4>{apiData?value_converter(apiData.statistics.commentCount):"Comments"}</h4>
        {
  commentData?.map((item, index) => (
    <div key={index} className="comment">
      <img src={item.snippet.topLevelComment.snippet.authorProfileImageUrl} alt="" />
      <div>
        <h3>
          {item.snippet.topLevelComment.snippet.authorDisplayName}{" "}
          <span>{moment(item.snippet.topLevelComment.snippet.publishedAt).fromNow()}</span>
        </h3>
        <p>{item.snippet.topLevelComment.snippet.textDisplay}</p>
        <div className="comment-action">
          <img src={like} alt="" />
          <span>{item.snippet.topLevelComment.snippet.likeCount || 0}</span>
          <img src={dislike} alt="" />
        </div>
      </div>
    </div>
  ))
}

      </div>
    </div>
  );
};

export default PlayVideo;
