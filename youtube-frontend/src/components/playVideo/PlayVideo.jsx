import React from 'react'
import './PlayVideo.css'
import video1 from '../../assets/video.mp4'
import like from '../../assets/like.png'
import dislike from '../../assets/dislike.png'
import share from '../../assets/share.png'
import save from '../../assets/save.png'
import jack from '../../assets/jack.png'
import user_profile from '../../assets/user_profile.jpg'
const PlayVideo = () => {
  return (
    <div className='play-video'>
      <video src={video1} controls autoPlay muted></video>
      <h3>Best YouTube channel to learn web development</h3>
      <div className="play-video-info">
        <p>1525 views &bull; 2 days ago</p>
        <div>
            <span><img src={like} alt="" />125</span>
            <span><img src={dislike} alt="" />2</span>
            <span><img src={share} alt="" />share</span>
            <span><img src={save} alt="" />save</span>
        </div>
      </div>
      <hr />
      <div className="publisher">
        <img src={jack} alt="" />
        <div>
            <p>GreatStack</p>
            <span>1M Subscribers</span>
        </div>
        <button>Subscribe</button>
      </div>
      <div className="vid-description">
        <p>Channel that makes learning easy</p>
        <p>Subscribe GreatStack to watch more tutorials on web development</p>
        <hr />
        <h4>130 Comments</h4>
        <div className="comment">
            <img src={user_profile} alt="" />
            <div>
                <h3>Randy Orton <span>1 day ago</span></h3>
                <p>Aglobal computer network providing a variety of information </p>
                <div className="comment-action">
                    <img src={like} alt="" />
                    <span>144</span>
                    <img src={dislike} alt="" />

                </div>
            </div>
        </div>
        <div className="comment">
            <img src={user_profile} alt="" />
            <div>
                <h3>Randy Orton <span>1 day ago</span></h3>
                <p>Aglobal computer network providing a variety of information </p>
                <div className="comment-action">
                    <img src={like} alt="" />
                    <span>144</span>
                    <img src={dislike} alt="" />
                    
                </div>
            </div>
        </div>
        <div className="comment">
            <img src={user_profile} alt="" />
            <div>
                <h3>Randy Orton <span>1 day ago</span></h3>
                <p>Aglobal computer network providing a variety of information </p>
                <div className="comment-action">
                    <img src={like} alt="" />
                    <span>144</span>
                    <img src={dislike} alt="" />
                    
                </div>
            </div>
        </div>
        <div className="comment">
            <img src={user_profile} alt="" />
            <div>
                <h3>Randy Orton <span>1 day ago</span></h3>
                <p>Aglobal computer network providing a variety of information </p>
                <div className="comment-action">
                    <img src={like} alt="" />
                    <span>144</span>
                    <img src={dislike} alt="" />
                    
                </div>
            </div>
        </div>
        <div className="comment">
            <img src={user_profile} alt="" />
            <div>
                <h3>Randy Orton <span>1 day ago</span></h3>
                <p>Aglobal computer network providing a variety of information </p>
                <div className="comment-action">
                    <img src={like} alt="" />
                    <span>144</span>
                    <img src={dislike} alt="" />
                    
                </div>
            </div>
        </div>
        <div className="comment">
            <img src={user_profile} alt="" />
            <div>
                <h3>Randy Orton <span>1 day ago</span></h3>
                <p>Aglobal computer network providing a variety of information </p>
                <div className="comment-action">
                    <img src={like} alt="" />
                    <span>144</span>
                    <img src={dislike} alt="" />
                    
                </div>
            </div>
        </div>
      </div>
    </div>
  )
}

export default PlayVideo
