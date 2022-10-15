import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
/* eslint-disable react/prop-types */
const SearchListItem = ({ data }) => {
  const createdAt = new Date(data.write_date).toLocaleDateString('en-us', {
    weekday: 'long',
    year: 'numeric',
    month: 'short',
    day: 'numeric',
    hour: 'numeric',
    minute: 'numeric',
  });
  const len = data.answers.length;

  return (
    <Container>
      <div className="left">
        <span>{data.score} votes</span>
        <span
          className={
            len === 0 ? 'default' : data.answered ? 'fullgreen' : 'whitegreen'
          }
        >
          {data.answered ? (
            <FontAwesomeIcon icon={faCheck} className="check" />
          ) : null}
          {len} answers
        </span>
        <span>{data.view_count} views</span>
      </div>
      <div className="right">
        <div className="question-title">
          <a href={`questiondetail/${data.post_id}`}>{data.post_name}</a>
        </div>
        <p className="content">{data.post_content}</p>
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
            <span className="date">asked {createdAt}</span>
          </div>
        </div>
      </div>
    </Container>
  );
};
export default SearchListItem;
const Container = styled.div`
  display: flex;
  padding-left: 20px;
  padding-top: 20px;
  border-bottom: 1px solid #cccdce;
  .left {
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    width: 120px;
    height: 90px;
    padding-right: 10px;
    span {
      font-size: 14px;
      margin-bottom: 5px;
    }
    .default {
      color: gray;
    }
    .whitegreen {
      color: #48a868;
      border: 1px solid #48a868;
      padding: 3px;
      border-radius: 3px;
    }
    .fullgreen {
      background-color: #48a868;
      color: white;
      padding: 3px 5px;
      border-radius: 3px;
    }
    .check {
      margin-right: 5px;
    }
    span:last-child {
      color: #f48225;
    }
  }
  .right {
    display: flex;
    flex-direction: column;
    width: 930px;
    height: 100px;
    padding-right: 10px;
    a {
      color: #257ed8;
      font-weight: 500;
      font-size: 18px;
      cursor: pointer;
      text-decoration: none;
    }
    a:hover {
      color: #62b5ff;
    }
    .content {
      color: #4c5155;
      margin-bottom: 3px;
      font-size: 14px;
    }
  }
  .right-bottom {
    display: flex;
    justify-content: space-between;
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
      padding: 3px;
      font-size: 13px;
      height: 23px;
      border-radius: 3px;
      background-color: transparent;
      white-space: nowrap;
      color: #39739d;
    }
  }
  .userinfo {
    font-size: 14px;
    .username {
      color: #257ed8;
      margin-right: 10px;
      cursor: pointer;
    }
    .username:hover {
      color: #62b5ff;
    }
    .date {
      color: #4c5155;
    }
  }
`;
