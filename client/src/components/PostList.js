import styled from 'styled-components';
import { Link } from 'react-router-dom';
/* eslint-disable react/prop-types */

const PostList = ({ data }) => {
  const createdAt = new Date(data.write_date).toLocaleDateString('en-us', {
    weekday: 'long',
    year: 'numeric',
    month: 'short',
    day: 'numeric',
    hour: 'numeric',
    minute: 'numeric',
  });

  return (
    <StyledPost>
      <div className="posts">
        <div className="stats-container">
          <div className="stats">
            {data.score} votes
            <div className="vote">
              <span className="vote-count">{data.answered}</span>
              <div className="count-text2">{data.answers.length} answers</div>
            </div>
            <div className="vote">
              <div className="count-text">{data.view_count} views</div>
            </div>
          </div>
        </div>
        <div className="summary">
          <div>
            <Link className="link" to={`/questiondetail/${data.post_id}`}>
              {data.post_name}
            </Link>
            <div className="content">{data.post_content}</div>
            <div className="right-bottom">
              <div className="tags">
                {data.tags.map((tag, idx) => (
                  <div key={idx} className="tagwrapper">
                    <span className="tag">{tag}</span>
                  </div>
                ))}
              </div>
              <div className="userinfo">
                <span className="username">{data.writer.nick_name}</span>
                <span className="date"> asked {createdAt}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </StyledPost>
  );
};

export default PostList;

const StyledPost = styled.div`
  @import url('https://fonts.googleapis.com/css2?family=Roboto&display=swap');
  font-family: Roboto, sans-serif;

  .posts {
    padding: 15px 0;
    width: 1070px;
    box-sizing: border-box;
    display: flex;
    border-top: 1px solid #d2d2d2;
  }

  .stats {
    display: flex;
    flex-direction: column;
    flex-shrink: 0;
    flex-wrap: wrap;
    align-items: flex-end;
    font-size: 15px;
  }

  .stats-container {
    width: 8%;
    color: #6a737c;
    margin-left: 20px;
    font-size: 11px;
  }
  .vote {
    padding: 0;
    margin-bottom: 8px;
    text-align: center;
    display: flex;

    .vote-count {
      font-size: 15px;
      margin-right: 2px;
    }
    .count-text2 {
      color: gray;
    }

    .count-text {
      font-size: 15px;
      color: #f48225;
    }
  }

  .answer {
    border: 2px solid #63b47c;
    background-color: #63b47c;
    color: white;
    border-radius: 3px;
    padding: 4px;

    .vote-count {
      color: white;
      font-size: 15px;
      padding: 1px;
    }
    .count-text {
      color: white;
      font-size: 12px;
      padding: 1px;
    }
  }

  .vote {
    padding: 0;
    margin-bottom: 8px;
    text-align: center;
    display: flex;

    .vote-count {
      font-size: 15px;
      margin-right: 2px;
    }

    .count-text {
      font-size: 15px;
    }

    .views {
      .count-text {
        font-size: 15px;
        color: #f48225;
      }
    }
  }

  .summary {
    margin-left: 30px;
    width: 90%;

    .link {
      color: #257ed8;
      font-weight: 500;
      font-size: 20px;
      cursor: pointer;
      text-decoration: none;
    }
    .link:hover {
      color: #87c6fe;
    }

    h3 {
      font-weight: 400;
      font-size: 15px;
      line-height: 1.4;
      margin-bottom: 7.5px;
    }
  }
  .content {
    color: #4c5155;
    margin-bottom: 10px;
    font-size: 16px;
  }
  .right-bottom {
    display: flex;
    justify-content: space-between;
    .userinfo {
      font-size: 14px;
      .username {
        color: #257ed8;
      }
      .date {
        color: #4c5155;
      }
    }
  }

  .tags {
    display: flex;
    justify-content: flex-start;
    .tagwrapper {
      cursor: pointer;
      margin-right: 5px;
      display: flex;
      padding-left: 5px;
      padding-right: 5px;
      border-radius: 3px;
      list-style: none;
      background-color: #e1ecf4;
    }
    .tagwrapper:hover {
      background-color: #d0e3f1;
    }
    .tag {
      border: none;
      padding: 0 3px;
      font-size: 13px;
      height: 28px;
      width: auto;
      border-radius: 3px;
      background-color: transparent;
      white-space: nowrap;
      color: #39739d;
    }
  }
`;
