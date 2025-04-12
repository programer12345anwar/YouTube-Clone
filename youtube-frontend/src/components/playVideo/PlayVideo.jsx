import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import "./PlayVideo.css";
import like from "../../assets/like.png";
import dislike from "../../assets/dislike.png";
import share from "../../assets/share.png";
import save from "../../assets/save.png";
import { API_KEY, value_converter } from "../../Data";
import moment from "moment";


const PlayVideo = () => {
  const { videoId } = useParams();
  const [apiData, setApiData] = useState(null);
  const [channelData, setChannelData] = useState(null);
  const [commentData, setCommentData] = useState(null);

  const fetchVideoData = async () => {
    const videoDetails_url = `https://youtube.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cstatistics&id=${videoId}&key=${API_KEY}`;
    try {
      const response = await fetch(videoDetails_url);
      const result = await response.json();
      setApiData(result.items?.[0] || null);
    } catch (error) {
      console.log(error);
    }
  };

  const fetchOtherData = async () => {
    if (!apiData?.snippet?.channelId) return;
    const channelDetails_url = `https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=${apiData.snippet.channelId}&key=${API_KEY}`;
    try {
      const response = await fetch(channelDetails_url);
      const result = await response.json();
      setChannelData(result.items?.[0] || null);
    } catch (error) {
      console.log(error);
    }
  };

  const fetchCommentData = async () => {
    const comment_url = `https://youtube.googleapis.com/youtube/v3/commentThreads?part=snippet%2Creplies&maxResults=50&videoId=${videoId}&key=${API_KEY}`;
    try {
      const response = await fetch(comment_url);
      const result = await response.json();
      setCommentData(result.items || []);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    fetchVideoData();
    fetchCommentData();
  }, [videoId]);

  useEffect(() => {
    if (apiData) fetchOtherData();
  }, [apiData]);

  return (
    <div className="play-video">
      {videoId && (
        <iframe
          src={`https://www.youtube.com/embed/${videoId}?autoplay=1`}
          frameBorder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
          referrerPolicy="strict-origin-when-cross-origin"
          allowFullScreen
        ></iframe>
      )}

      <h3>{apiData?.snippet?.title || "Title Here"}</h3>

      <div className="play-video-info">
        <p>
          {apiData ? value_converter(apiData.statistics.viewCount) : "16K"} views &bull;{" "}
          {apiData ? moment(apiData.snippet.publishedAt).fromNow() : ""}
        </p>
        <div>
          <span>
            <img src={like} alt="" />
            {apiData ? value_converter(apiData.statistics.likeCount) : "16K"}
          </span>
          <span><img src={dislike} alt="" /></span>
          <span><img src={share} alt="" />share</span>
          <span><img src={save} alt="" />save</span>
        </div>
      </div>

      <hr />

      <div className="publisher">
        <img src={channelData?.snippet?.thumbnails?.medium?.url || null} alt="" />
        <div>
          <p>{apiData?.snippet?.channelTitle || "Channel Name"}</p>
          <span>
            {channelData ? value_converter(channelData.statistics.subscriberCount) : "16K"} subscribers
          </span>
        </div>
        <button>Subscribe</button>
      </div>

      <div className="vid-description">
        <p>{apiData?.snippet?.description?.slice(0, 250) || "Description Here"}</p>
        <hr />
        <h4>{apiData ? value_converter(apiData.statistics.commentCount) : "Comments"}</h4>
        {commentData?.map((item, index) => (
          <div key={index} className="comment">
            <img
              src={item.snippet.topLevelComment.snippet.authorProfileImageUrl}
              alt=""
            />
            <div>
              <h3>
                {item.snippet.topLevelComment.snippet.authorDisplayName}{" "}
                <span>
                  {moment(item.snippet.topLevelComment.snippet.publishedAt).fromNow()}
                </span>
              </h3>
              <p>{item.snippet.topLevelComment.snippet.textDisplay}</p>
              <div className="comment-action">
                <img src={like} alt="" />
                <span>
                  {value_converter(item.snippet.topLevelComment.snippet.likeCount)}
                </span>
                <img src={dislike} alt="" />
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default PlayVideo;
