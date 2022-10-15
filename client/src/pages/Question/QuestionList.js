import Nav from '../../components/Nav';
import MyFooter from '../../components/MyFooter';
import styled from 'styled-components';
import MyButton from '../../components/MyButton';
import { useNavigate } from 'react-router-dom';
import PostList from '../../components/PostList';
/* eslint-disable react/prop-types */

const QuestionList = ({ lists }) => {
  const navigate = useNavigate();
  return (
    <>
      <StyledMain>
        <Nav />
        <MainWrapper>
          <HeaderRow>
            <StyledHeader>
              <h1>All Questions</h1>
            </StyledHeader>
            <BtnWrapper>
              <MyButton
                text={`Ask Question`}
                type={'blue'}
                onClick={() => navigate('/questionwrite')}
              />
            </BtnWrapper>
          </HeaderRow>
          <QuestionCountRow>
            <span>{lists.length} questions</span>
            <ButtonRow>
              <MyButton text={'Today'} type={'default'} />
              <MyButton text={`Week`} type={'default'} />
              <MyButton text={`Month`} type={'default'} />
              <MyButton text={`Year`} type={'blue'} />
            </ButtonRow>
          </QuestionCountRow>
          {lists.map((el, idx) => (
            <PostList key={idx} data={el} />
          ))}
        </MainWrapper>
      </StyledMain>
      <MyFooter />
    </>
  );
};

export default QuestionList;
const HeaderRow = styled.div`
  display: flex;
  justify-content: space-between;
  height: 100px;
  padding-top: 30px;
  padding-left: 20px;
  width: 1070px;
`;
const ButtonRow = styled.div`
  display: flex;
  height: 100px;
  padding-top: 30px;
  padding-left: 20px;
  button {
    width: 50px;
    font-size: 12px;
    height: 35px;
    border-left: 0.5px solid #54595e;
    border-top: 0.5px solid #54595e;
    border-bottom: 0.5px solid #54595e;
    color: #54595e;
    border-radius: 0px;
  }
  button:nth-of-type(4) {
    color: white;
    border: 0.5px solid #0a95ff;
  }
`;
const StyledHeader = styled.header`
  display: flex;
`;

const BtnWrapper = styled.div`
  height: 200px;
  width: 150px;
  button {
    width: 150px;
    height: 40px;
    font-size: 16px;
  }
`;

const StyledMain = styled.main`
  display: flex;

  @import url('https://fonts.googleapis.com/css2?family=Roboto&display=swap');
  font-family: Roboto, sans-serif;
`;

const MainWrapper = styled.div`
  display: flex;
  flex-direction: column;
`;
const QuestionCountRow = styled.div`
  margin-bottom: 12px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  padding-left: 24px;
  padding-right: 3px;
`;
