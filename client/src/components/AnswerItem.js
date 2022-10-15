/* eslint-disable react/prop-types */
import axios from 'axios';
import { useState } from 'react';
import styled from 'styled-components';
import AnswerVote from './AnswerVote';
import { getLoginCookie } from '../lib/cookie';
import { useSelector } from 'react-redux';
const AnswerItem = ({ data, pid }) => {
  const state = useSelector((state) => state.signInReducer);
  const [content, setContent] = useState(data.content);
  // const userData = JSON.parse(localStorage.getItem('userData'));
  // let memberId = parseInt(userData.memberId);
  let aid = data.answer_id;
  const [isEdit, setIsEdit] = useState(false);
  const [votes, setVotes] = useState(0);
  const createdAt = new Date(data.create_date).toLocaleDateString('en-us', {
    hour: 'numeric',
    minute: 'numeric',
  });
  const deleteAnswer = () => {
    axios
      .delete(
        `http://3.39.180.45:56178/DBtest/deleteAnswer/${data.answer_id}`,
        {
          headers: { Authorization: getLoginCookie() },
        }
      )
      .then((res) => {
        console.log(res.data);
        console.log('답변 삭제성공');
      });
  };
  const editAnswer = () => {
    const putForm = {
      post_id: pid,
      member_id: state.data.memberid,
      content: content,
    };
    axios
      .put(
        `http://3.39.180.45:56178/DBtest/updateAnswer/${data.answer_id}`,
        putForm,
        { headers: { Authorization: getLoginCookie() } }
      )
      .then((res) => {
        console.log(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const onIncreaseVote = () => {
    let data = {};
    axios
      .post(
        `http://3.39.180.45:56178/DBtest/answer_vote?vote=+1&member_id=${state.data.memberId}&answer_id=${aid}`,
        data,
        { headers: { Authorization: getLoginCookie() } }
      )
      .then(() => {
        setVotes(votes + 1);
      });
  };
  const onDecreaseVote = () => {
    axios
      .post(
        `http://3.39.180.45:56178/DBtest/answer_vote?vote=-1&member_id=${state.data.memberId}&answer_id=${aid}`,
        data,
        { headers: { Authorization: getLoginCookie() } }
      )
      .then(() => {
        setVotes(votes - 1);
      });
  };
  return (
    <>
      <Container>
        <div className="left">
          <AnswerVote
            onIncreaseVote={onIncreaseVote}
            onDecreaseVote={onDecreaseVote}
            votes={votes}
          />
        </div>
        <div className="right">
          <div className="content">
            <div>{data.content}</div>
          </div>
          <div className="content-bottom">
            <div className="btns">
              {state.loginState &&
              state.data.memberId === parseInt(data.member_id) ? (
                <>
                  <button onClick={() => setIsEdit(true)}>Edit</button>
                  <button onClick={() => deleteAnswer()}>Delete</button>
                </>
              ) : null}
            </div>
            <div className="userinfo">
              <span>Answered </span>
              <br />
              <span>{createdAt}</span>
              <span className="name">{data.id}</span>
            </div>
          </div>
          {isEdit ? (
            <Edit>
              <textarea
                defaultValue={content}
                onChange={(e) => setContent(e.target.value)}
              ></textarea>
              <div className="btn">
                <button onClick={editAnswer}>Edit</button>
                <button onClick={() => setIsEdit(false)}>Cancel</button>
              </div>
            </Edit>
          ) : null}
        </div>
      </Container>
    </>
  );
};

export default AnswerItem;
const Container = styled.div`
  display: flex;
  width: 1070px;
  align-items: flex-start;
  border-bottom: 1px solid lightgray;
  .right {
    margin-left: 10px;
    padding: 10px;
    width: 1070px;
  }
  .content {
    min-height: 50px;
  }
  .content-bottom {
    width: 1030px;
    display: flex;
    justify-content: space-between;
    font-size: 14px;
    font-weight: 400;
    button {
      background-color: transparent;
      border: none;
      color: gray;
      margin-right: 7px;
      cursor: pointer;
    }
    a {
      text-decoration: none;
      color: gray;
      margin-right: 7px;
      cursor: pointer;
    }
    .userinfo {
      color: #a4a4a4;
    }
    .name {
      color: #0a95ff;
      margin-left: 5px;
      cursor: pointer;
    }
    .name:hover {
      color: #2d95ff;
    }
  }
`;
const Edit = styled.div`
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  textarea {
    min-height: 50px;
    min-width: 850px;
    font-size: 14px;
    color: #363b3f;
    text-indent: 10px;
    border: 1px solid #8a939b;
    margin-bottom: 15px;
    border-radius: 5px;
    padding-top: 5px;
  }
  textarea:focus {
    border: 1px solid cornflowerblue;
    border-radius: 2px;
    outline: none;
    box-shadow: 0 0 0 3px #cde9fe;
  }
  .btn {
    display: flex;
    justify-content: flex-end;
    min-width: 850px;
  }
  button {
    background-color: transparent;
    border: none;
    margin-left: 10px;
    color: gray;
    cursor: pointer;
  }
`;
