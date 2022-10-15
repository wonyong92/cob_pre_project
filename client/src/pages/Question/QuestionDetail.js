/* eslint-disable react/prop-types */
import axios from 'axios';
import { useEffect, useState } from 'react';
import Nav from '../../components/Nav';
import MyFooter from '../../components/MyFooter';
import MyButton from '../../components/MyButton';
import Answers from '../../components/Answers';
import styled from 'styled-components';
import PostAnswer from '../../components/PostAnswer';
import { Link, useNavigate, useParams } from 'react-router-dom';
import PostVote from '../../components/PostVote';
import { getLoginCookie } from '../../lib/cookie';
import { useSelector } from 'react-redux';
const QuestionDetail = ({ getAllPost }) => {
  const state = useSelector((state) => state.signInReducer);
  const navigate = useNavigate();
  const [data, setData] = useState([]);
  const [pid, setPid] = useState(0);
  const [answerList, setAnswerList] = useState([]);
  const [count, setCount] = useState(0);
  const [votes, setVotes] = useState(0);
  const [loading, setLoading] = useState(true);
  // const userData = JSON.parse(localStorage.getItem('userData'));
  // let memberId = parseInt(userData.memberId);
  let params = useParams();
  // console.log(state);

  const createdAt = new Date(data.write_date).toLocaleDateString('en-us', {
    weekday: 'long',
    year: 'numeric',
    month: 'short',
    day: 'numeric',
    hour: 'numeric',
    minute: 'numeric',
  });
  const updatedAt = new Date(data.modified_date).toLocaleDateString('en-us', {
    weekday: 'long',
    year: 'numeric',
    month: 'short',
    day: 'numeric',
    hour: 'numeric',
    minute: 'numeric',
  });
  const createdAt2 = new Date(data.write_date).toLocaleDateString('en-us', {
    hour: 'numeric',
    minute: 'numeric',
  });

  useEffect(() => {
    getQuestionDetail();
    getAnswer();
  }, []);
  const getQuestionDetail = () => {
    axios
      .get(`http://3.39.180.45:56178/DBtest/getPost?post_id=${params.id}`)
      .then((res) => {
        setData(res.data.post);
        setPid(res.data.post.post_id);
        setVotes(res.data.post.score);
        setLoading(false);
      });
  };
  const getAnswer = () => {
    axios
      .get(
        `http://3.39.180.45:56178/DBtest/findAnswers/${params.id}?page=1&size=15`
      )
      .then((res) => {
        setAnswerList([...res.data.data]);
        setCount(res.data.page_info.total_elements);
      });
  };
  if (loading) return null;

  const onIncreaseVote = () => {
    let data = {};
    axios
      .post(
        `http://3.39.180.45:56178/DBtest/post_vote?vote=+1&member_id=${state.data.memberId}&post_id=${params.id}`,
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
        `http://3.39.180.45:56178/DBtest/post_vote?vote=-1&member_id=${state.data.memberId}&post_id=${params.id}`,
        data,
        { headers: { Authorization: getLoginCookie() } }
      )
      .then(() => {
        setVotes(votes - 1);
      });
  };
  const deleteQuestion = () => {
    axios
      .delete(`http://3.39.180.45:56178/DBtest/delete/${params.id}`, {
        headers: { Authorization: getLoginCookie() },
      })
      .then(() => {
        getAllPost();
        navigate('/');
      });
  };

  return (
    <Container>
      <Wrapper>
        <Nav />
        <Detail>
          <div className="quesitonBox">
            <div className="top">
              <div className="title">
                <h1>{data.post_name}</h1>
                <MyButton
                  text={'Ask Question'}
                  type={'blue'}
                  onClick={() => navigate('/questionwrite')}
                />
              </div>
              <div className="info">
                <span>Asked {createdAt}</span>
                <span>Modified {updatedAt}</span>
                <span>Viewed {data.view_count}</span>
              </div>
            </div>
            <div className="bottom">
              <PostVote
                votes={votes}
                setVotes={setVotes}
                onIncreaseVote={onIncreaseVote}
                onDecreaseVote={onDecreaseVote}
                pid={pid}
              />
              <div className="content">
                <p>질문내용 {data.post_content}</p>
                <div className="tags">
                  {data.tags.map((tag, idx) => (
                    <div key={idx} className="tagwrapper">
                      <div className="tag">{tag}</div>
                    </div>
                  ))}
                </div>
                <div className="content-bottom">
                  <div className="btns">
                    {state.loginState === true &&
                    parseInt(state.data.memberId) ===
                      parseInt(data.writer.member_id) ? (
                      <>
                        <Link
                          to={`/editquestion/${data.post_id}`}
                          state={{ data: data }}
                        >
                          Edit
                        </Link>
                        <button onClick={deleteQuestion}>Delete</button>
                      </>
                    ) : null}
                  </div>
                  <div className="userinfo">
                    <span>Asked </span>
                    <br />
                    <span>{createdAt2}</span>
                    <span className="name">{data.writer.nick_name}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div className="answerBox">
            <Answers
              pid={pid}
              answerList={answerList}
              setAnswerList={setAnswerList}
              count={count}
              getAnswerAnswer={getAnswer}
            />
            <div>
              <PostAnswer pid={pid} getAnswerAnswer={getAnswer} />
            </div>
          </div>
        </Detail>
      </Wrapper>
      <MyFooter />
    </Container>
  );
};

export default QuestionDetail;

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;
const Wrapper = styled.div`
  display: flex;
`;
const Detail = styled.div`
  display: flex;
  flex-direction: column;
  border-left: 1px solid #cccdce;
  width: 1070px;
  .quesitonBox {
    display: flex;
    flex-direction: column;
    margin: 30px;
    .top {
      display: flex;
      flex-direction: column;
      width: 1070px;
      border-bottom: 1px solid lightgray;
      .title {
        display: flex;
        justify-content: space-between;
        margin-bottom: 10px;
        h1 {
          font-weight: 500;
        }
      }
      .info {
        width: auto;
        margin-bottom: 10px;
        margin-left: 3px;
        span {
          margin-right: 15px;
          font-size: 14px;
        }
      }
    }
    .bottom {
      display: flex;
      width: 1000px;
      align-items: flex-start;
      .content {
        padding: 20px;
        width: 1000px;
        min-height: 100px;
        .tags {
          display: flex;
          justify-content: flex-start;
          margin-top: 20px;
          margin-bottom: 20px;
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
      }
      .content-bottom {
        width: 1030px;
        display: flex;
        justify-content: space-between;
        font-size: 14px;
        font-weight: 400;

        .userinfo {
          border-radius: 5px;
          padding: 10px;
          background-color: #d9eaf7;
        }
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
        .name {
          color: #0a95ff;
          margin-left: 5px;
          cursor: pointer;
        }
      }
    }
  }
  .answerBox {
    margin-left: 30px;
  }
`;
