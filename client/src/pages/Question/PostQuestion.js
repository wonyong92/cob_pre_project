import { Fragment, useState, useEffect } from 'react';
import MyButton from '../../components/MyButton';
import styled from 'styled-components';
import Widget from '../../components/Widget';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import PostBodyTextarea from '../../components/PostBodyTextarea';
import { getLoginCookie } from '../../lib/cookie';
import Tags from '../../components/Tags';
/* eslint-disable react/prop-types */
/* eslint-disable react/jsx-key */

const PostQuestion = () => {
  const [title, setTitle] = useState('');
  const [body, setBody] = useState('');
  const userData = JSON.parse(localStorage.getItem('userData'));
  let memberid = userData.memberId;
  const navigate = useNavigate();
  const [tagList, setTagList] = useState([]);
  const [tags, setTags] = useState([]);
  const [tagData, setTagData] = useState([]);
  const [msg, setMsg] = useState(<p></p>);

  useEffect(() => {
    axios.get(`http://3.39.180.45:56178/DBtest/tagFinds`).then((res) => {
      setTagList(res.data.tags);
    });
  }, []);

  useEffect(() => {
    setTagData(tags.map((el) => el.name));
  }, [tags]);

  const handleSubmit = (event) => {
    event.preventDefault();
    for (let i = 0; i < tagData.length; i++) {
      if (tagData[0] === '') {
        setMsg(<p>Please check your title or body.. Some data is missing.</p>);
      } else if (title === '' || body === '') {
        setMsg(<p>Please check your title or body.. Some data is missing.</p>);
      } else {
        setMsg(<p></p>);
        axios
          .post(
            'http://3.39.180.45:56178/DBtest/post',
            {
              post_name: title,
              post_content: body,
              member_id: memberid,
              tags: tagData, // body에 보낼 태그
            },
            { headers: { Authorization: getLoginCookie() } }
          )
          .then(() => {
            navigate(`/`);
          })
          .catch((err) => {
            console.log(err);
          });
      }
    }
  };
  return (
    <Fragment>
      <StyledHeaderRow>
        <h1>Ask a Public question</h1>
      </StyledHeaderRow>
      <Container>
        <PostContainer>
          <h3>Title</h3>
          <div>
            Be specific and imagine you &apos; re asking a question to another
            person
          </div>
          <QuestionTitleInput
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            type="text"
            placeholder="e.g Is there an R function for finding the index of an element in a vector?"
            title={title}
          />
          <h3>Body</h3>
          <span>
            Include all the information someone would need to answer your
            question
          </span>
          <PostBodyTextarea value={body} handlePostBodyChange={setBody} />
          <h3>Tag Name</h3>
          <span>Add up to 5 tags to describe what your question is about</span>
          <div>
            <Tags tagList={tagList} tags={tags} setTags={setTags} />
          </div>

          <StyledMyButton>
            <MyButton
              text={'Post your question'}
              type={'blue'}
              onClick={(event) => handleSubmit(event)}
            />
          </StyledMyButton>
          {msg}
        </PostContainer>
        <Widget />
      </Container>
    </Fragment>
  );
};

export default PostQuestion;
const PostContainer = styled.div`
  @import url('https://fonts.googleapis.com/css2?family=Roboto&display=swap');
  font-family: Roboto, sans-serif;
  display: flex;
  flex-direction: column;
  background-color: white;
  margin-left: 30px;
  padding: 30px 30px 30px;
  width: 876px;
  height: 587.17px;
  border-radius: 10px;
  position: center;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
  h3 {
    font-weight: 600;
  }
`;

const QuestionTitleInput = styled.input`
  background: none;
  border: 1px solid #777;
  border-radius: 3px;
  display: block;
  width: 100%;
  box-sizing: border-box;
  padding: 10px;
  margin-bottom: 20px;
  h3 {
    font-weight: 600;
  }
`;

// const QuestionTagInput = styled.input`
//   background: none;
//   border: 1px solid #777;
//   border-radius: 3px;
//   display: block;
//   width: 100%;
//   box-sizing: border-box;
//   padding: 10px;
//   margin-bottom: 20px;
//   h3 {
//     font-weight: 100;
//   }
// `;

const StyledMyButton = styled.div`
  margin-top: 30px;

  display: flex;
`;

const StyledHeaderRow = styled.header`
  @import url('https://fonts.googleapis.com/css2?family=Roboto&display=swap');
  font-family: Roboto, sans-serif;
  display: flex;
  background-color: #f1f2f3;
  align-items: center;
  height: 130px;
  background-image: url('https://cdn.sstatic.net/img/ask/background.svg?v=2e9a8205b368');
  background-repeat: no-repeat;
  background-position: right bottom !important;
  padding-top: 24px !important;
  padding-bottom: 24px !important;
  padding-left: 31px;
`;

const Container = styled.div`
  display: flex;
  background-color: #f1f2f3;
`;
