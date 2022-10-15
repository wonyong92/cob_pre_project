import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faStackOverflow } from '@fortawesome/fontawesome-free-brands';

const MyFooter = () => {
  return (
    <Container>
      <Footer>
        <Logo>
          <FontAwesomeIcon icon={faStackOverflow} className="logo" />
        </Logo>
        <Col>
          <strong>STACK OVERFLOW</strong>
          <span>Questions</span>
          <span>Help</span>
        </Col>
        <Col>
          <strong>PRODUCTS</strong>
          <span>Teams</span>
          <span>Advertising</span>
          <span>Collectives</span>
          <span>Talent</span>
        </Col>
        <Col>
          <strong>COMPANY</strong>
          <span>About</span>
          <span>Press</span>
          <span>Work Here</span>
          <span>Legal</span>
          <span>Privacy Policy</span>
          <span>Terms of Service</span>
          <span>Contact Us</span>
          <span>Cookie Settings</span>
          <span>Cookie Policy</span>
        </Col>
        <Col>
          <strong>STACK EXCHANGE NETWORD</strong>
          <span>Technology</span>
          <span>Culture & recreation</span>
          <span>Life & arts</span>
          <span>Science</span>
          <span>Professional</span>
          <span>Business</span>
          <span>API</span>
          <span>Data</span>
        </Col>
        <LastCol>
          <div>
            <span>Blog</span>
            <span>Facebook</span>
            <span>Twitter</span>
            <span>Linkedin</span>
            <span>Instagram</span>
          </div>
          <p>
            Site design / logo © 2022 Stack Exchange Inc; user contributions
            licensed under CC BY-SA. rev 2022.9.1.42957
          </p>
        </LastCol>
      </Footer>
    </Container>
  );
};

export default MyFooter;

const Container = styled.div`
  background-color: #24262a;
  height: 320px;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
`;
const Footer = styled.footer`
  width: 1330px;
  height: 200px;
  display: flex;
  justify-content: center;
  align-items: space-around;
  color: #babfc4;
`;
const Col = styled.div`
  display: flex;
  flex-direction: column;
  width: 15%;
  font-size: 12px;
  strong {
    margin-bottom: 10px;
  }
  span {
    margin-bottom: 5px;
  }
`;
const LastCol = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 28%;
  font-size: 12px;
  margin-left: 50px;
  div {
    display: flex;
    span {
      margin-right: 10px;
    }
    p {
    }
  }
`;
const Logo = styled.div`
  display: flex;
  justify-content: center;
  width: 5%;
  margin-right: 20px;
  .logo {
    font-size: 2rem;
  }
`;
