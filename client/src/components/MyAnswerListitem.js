import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import styled from 'styled-components';
import { PostInfo } from './MyQuestionListItem';
/* eslint-disable react/prop-types */

const MyAnswerListItem = ({ data }) => {
  const createdAt =
    new Date().getHours() - new Date(data.write_date).getHours();
  return (
    <ItemContainer>
      <PostInfo>
        <div className="infoWrapper">
          <div className="numbers">
            <span>{data.score} votes</span>
          </div>
          <span className="accepted">
            {data.accepted ? (
              <FontAwesomeIcon className="green" icon={faCheck} />
            ) : (
              <p>not accepted</p>
            )}
          </span>
        </div>
        <a href={`questiondetail/${data.post_id}`}>
          <h4 className="myTitle">{data.answer_content}</h4>
        </a>
      </PostInfo>
      <div className="dateWrapper">
        <div>
          <span className="date">answered {createdAt} hours ago</span>
        </div>
      </div>
    </ItemContainer>
  );
};
export default MyAnswerListItem;

export const ItemContainer = styled.li`
  display: flex;
  justify-content: space-between;
  list-style: none;
  margin-bottom: 15px;
  margin-right: 20px;
  width: 1030px;
  border-bottom: 1px solid #e3e6e8;
  padding-bottom: 20px;
  .infoWrapper {
    display: flex;
  }
  .mypostInfo {
    display: flex;
    width: 100%;
    min-height: 25px;
    justify-content: space-between;
  }
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
  .accepted {
    color: #808080;
    font-size: 14px;
  }
`;
