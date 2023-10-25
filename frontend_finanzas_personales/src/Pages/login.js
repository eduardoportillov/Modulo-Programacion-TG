import React, { Component } from 'react';
import { connect } from 'react-redux';
import { SForm, SHr, SIcon, SInput, SNavigation, SPage, SPopup, SText, STheme, SView } from 'servisofts-component';
import CryptoJS from 'crypto-js';
import Model from '../Model';
import { Link } from '../Components';
export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    handleLogin = (data) => {
        this.setState({ loading: true, error: "" })
        Model.user.login({
            email: data.email,
            password: data.password
        }).then(e => {
            console.log(e);
            Model.user.setJWT(e);
            SNavigation.replace("/");
            this.setState({ loading: false, error: "" })
        }).catch(e => {
            console.error(e);
            this.setState({ loading: false, error: e })
        })
    }
    render() {

        return (
            <SPage title={'Login'} hidden >
                <SView col={"xs-12"} center>
                    <SHr height={50} />
                    <SView width={300} height={100} center>
                        <SIcon name={"Logo"} fill={STheme.color.secondary} />
                    </SView>
                    <SHr height={50} />
                    <SText fontSize={18}>Iniciar sesión</SText>
                    <SHr height={16} />
                    <SForm
                        col={"xs-11 sm-10 md-8 lg-6 xl-4"}
                        ref={ref => this.form = ref}
                        inputs={{
                            email: {
                                label: "Correo",
                                // type: 'email',
                                required: true,
                                autoFocus: true,
                                keyboardType: 'email-address',
                                onKeyPress: (evt) => {
                                    if (evt.key === 'Enter') {
                                        this.form.focus('password');
                                    }
                                },
                            },
                            password: {
                                label: "Contraseña",
                                type: "password",
                                required: true,
                                onKeyPress: (evt) => {
                                    if (evt.key === 'Enter') {
                                        this.form.submit();
                                    }
                                },
                            }
                        }}
                        loading={this.state.loading}
                        error={this.state.error}

                        onSubmitName={"Ingresar"}
                        onSubmitProps={{
                            type: "outline"
                        }}
                        onSubmit={(data) => {
                            this.handleLogin(data);
                        }}
                    />
                    <SHr h={20} />
                    <Link src={"/registro"} />
                </SView>
            </SPage>
        );
    }
}