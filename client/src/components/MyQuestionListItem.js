import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
/* eslint-disable react/prop-types */

const MyQuestionListItem = ({ data }) => {
  const createdAt =
    new Date().getHours() - new Date(data.write_date).getHours();
  const tags = data.tags;
  const len = data.answers.length;
  return (
    <ItemContainer>
      <PostInfo>
        <div className="numbers">
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
        <div className="title">
          <a href={`questiondetail/${data.post_id}`}>
            <span className="myTitle">{data.post_name}</span>
          </a>
        </div>
        <div className="tags">
          {tags.map((el, idx) => (
            <div key={idx}>
              <span className="tag-title">{el}</span>
            </div>
          ))}
        </div>
      </PostInfo>
      <div className="dateWrapper">
        <span className="date">asked {createdAt} hours ago</span>
      </div>
    </ItemContainer>
  );
};
export default MyQuestionListItem;

export const ItemContainer = styled.li`
  display: flex;
  justify-content: space-between;
  list-style: none;
  margin-bottom: 15px;
  margin-right: 20px;
  width: 1030px;
  border-bottom: 1px solid #e3e6e8;
  padding-bottom: 20px;
  .myTitle {
    font-size: 18px;
  }
  a {
    text-decoration: none;
    color: #333536;
  }
  .dateWrapper {
    display: flex;
    align-items: flex-end;
    .date {
      color: #808080;
      font-size: 14px;
    }
  }
`;

export const PostInfo = styled.div`
  display: flex;
  flex-direction: column;

  .numbers {
    font-size: 14px;
    span {
      margin-right: 5px;
      color: #808080;
    }
    span:first-child {
      color: #333536;
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
  }
  .myTitle {
    color: #257ed8;
    font-size: 18px;
    cursor: pointer;
  }
  .myTitle:hover {
    color: #62b5ff;
  }
  .tags {
    display: flex;
    flex-direction: row;
    div {
      cursor: pointer;
      margin-right: 5px;
      display: flex;
      padding-left: 5px;
      padding-right: 5px;
      border-radius: 3px;
      list-style: none;
      background-color: #e1ecf4;
    }
    div:hover {
      background-color: #d0e3f1;
    }
    .tag-title {
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
`;
