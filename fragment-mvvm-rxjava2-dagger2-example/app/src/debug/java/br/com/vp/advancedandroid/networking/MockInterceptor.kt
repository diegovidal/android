package br.com.vp.advancedandroid.networking

import br.com.vp.advancedandroid.settings.DebugPreferences
import okhttp3.*
import javax.inject.Inject

/**
 * @author diegovidal on 10/05/2018.
 */
class MockInterceptor @Inject
        constructor(private val mockResponseFactory: MockResponseFactory,
                    private val debugPreferences: DebugPreferences)
    : Interceptor{

    override fun intercept(chain: Interceptor.Chain?): Response? {

        if (debugPreferences.useMockResponsesEnabled()){

            chain?.request().let {

                val mockResponse = mockResponseFactory.getMockResponse(chain?.request()!!)
                mockResponse?.let {
                    return Response.Builder()
                            .message("")
                            .protocol(Protocol.HTTP_1_1)
                            .request(chain.request())
                            .code(200)
                            .body(ResponseBody.create(MediaType.parse("text/json"), mockResponse))
                            .build()
                }
            }

        }
        return chain?.proceed(chain.request())
    }
}