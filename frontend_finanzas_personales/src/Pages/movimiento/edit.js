import React, {Component} from 'react';
import {
    SForm,
    SList,
    SNavigation,
    SPage,
    SText,
    STheme,
    SView,
} from 'servisofts-component';
import Model from '../../Model';
import {Container} from '../../Components';

export default class index extends Component {
    constructor(props) {
        super(props);
        this.pk = SNavigation.getParam('pk');
    }
    state = {};
    componentDidMount() {
        Model.movimiento.getbykey({keyMovimiento: this.pk}).then(e => {
            this.setState({movimiento: e});

            Model.categoriamovimiento.getalluser().then(arraycc => {
                const cm = arraycc.find(a => a.key == e.keyCategoria);
                this.setState({cm: cm});
            });
        });
    }

    render() {
        return (
            <SPage>
                <Container loading={!this.state.movimiento}>
                    <SForm
                        inputs={{
                            keyCategoria: {
                                type: 'text',
                                label: 'Categoria.',
                                isRequired: true,
                                value: this.state?.cm?.nombre,
                                editable: false,
                                onPress: () => {
                                    SNavigation.navigate(
                                        '/categoriamovimiento',
                                        {
                                            onSelect: cc => {
                                                this.setState({cm: cc});
                                                SNavigation.goBack();
                                            },
                                        }
                                    );
                                },
                            },

                            monto: {
                                type: 'money',
                                label: 'Monto.',
                                defaultValue: parseFloat(
                                    this.state?.movimiento?.monto
                                ).toFixed(2),
                                col: 'xs-10',
                                isRequired: true,
                            },

                            descripcion: {
                                type: 'textArea',
                                label: 'Descripcion.',
                                defaultValue:
                                    this.state?.movimiento?.descripcion,
                                isRequired: true,
                            },
                        }}
                        onSubmitName={'SUBIR'}
                        loading={this.state.loading}
                        error={this.state.error}
                        onSubmit={e => {
                            e.keyCategoria = this.state?.cm?.key
                            this.setState({loading: true});
                            Model.movimiento
                                .edit(e, this.state?.movimiento?.key)
                                .then(e => {
                                    this.setState({loading: false});
                                    console.log(e);
                                    SNavigation.goBack();
                                })
                                .catch(e => {
                                    console.error(e);
                                    this.setState({loading: false, error: e});
                                });
                        }}
                    />
                </Container>
            </SPage>
        );
    }
}
